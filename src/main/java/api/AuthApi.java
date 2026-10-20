package api;

import entity.AuthRequest;

public interface AuthApi {

    String authorize(AuthRequest authRequest);
}
