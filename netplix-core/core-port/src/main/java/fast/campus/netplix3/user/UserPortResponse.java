package fast.campus.netplix3.user;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPortResponse {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String provider;
    private String providerId;
    private String role;
}
