package fast.campus.netplix3.controller.user.request;

import fast.campus.netplix3.annotation.PasswordEncryption;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    private final String username;

    @PasswordEncryption
    private final String password;
    private final String email;
    private final String phone;

    public UserRegisterRequest(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
