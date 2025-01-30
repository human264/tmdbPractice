package fast.campus.netplix3.user.command;


import fast.campus.netplix3.user.NetplixUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@RequiredArgsConstructor
@Builder
public class UserResponse {
    private final String userId;
    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final String provider;
    private final String providerId;
    private final String role;

    public static UserResponse toUserResponse(NetplixUser netplixUser) {
        return new UserResponse(
                netplixUser.getUserId(),
                netplixUser.getUsername(),
                StringUtils.defaultIfBlank(netplixUser.getEncryptedPassword(), "password"),
                StringUtils.defaultIfBlank(netplixUser.getEmail(), "email@email.com"),
                StringUtils.defaultIfBlank(netplixUser.getPhone(), "010-0000-0000"),
                StringUtils.defaultIfBlank(netplixUser.getProvider(), "no-provider"),
                StringUtils.defaultIfBlank(netplixUser.getProviderId(), "no-provider-id"),
                netplixUser.getRole()
        );
    }
}
