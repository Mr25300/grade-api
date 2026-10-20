package api;

import entity.AuthRequest;
import entity.Grade;
import entity.Session;
import entity.UsernameAuthRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TokenAuthApi implements AuthApi {

    public static final String STATUS_CODE = "status_code";
    private static final String TOKEN = "token";
    // Defining some constants.
    private static final String API_URL = "https://csc207-20269-vm.teach.cs.toronto.edu";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final int SUCCESS_CODE = 200;
    private Session session;

    public TokenAuthApi(Session session) {
        this.session = session;
    }
    @Override
    public String authorize(AuthRequest authRequest) {

        if (!(authRequest instanceof UsernameAuthRequest)) {
            throw new IllegalArgumentException("Request invalid: not a username request.");
        }

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/signUp?username=%s", API_URL, ((UsernameAuthRequest) authRequest).getUsername()))
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        // Hint: look at the API documentation to understand what the response looks like.
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE) == SUCCESS_CODE) {
                String token = responseBody.getString(TOKEN);
                session.setToken(token);
                return token;
            }
            else {
                throw new RuntimeException("Sign up failed");
            }
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }
}
