package entity;

public class UsernameAuthRequest extends AuthRequest {
    private String username;

    public UsernameAuthRequest(String username){
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
