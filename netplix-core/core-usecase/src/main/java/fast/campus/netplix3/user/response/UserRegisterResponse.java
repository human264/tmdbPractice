package fast.campus.netplix3.user.response;

import lombok.Getter;

@Getter
public class UserRegisterResponse {
    private final String username;
    private final String email;
    private final String phone;

    public UserRegisterResponse(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
