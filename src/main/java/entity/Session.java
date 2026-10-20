package entity;

public class Session {
    private String token;

    public Session() {
        this(null);
    }
    public Session(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
