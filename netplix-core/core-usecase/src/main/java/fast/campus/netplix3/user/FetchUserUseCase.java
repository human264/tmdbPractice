package fast.campus.netplix3.user;

import fast.campus.netplix3.user.command.UserResponse;
import fast.campus.netplix3.user.response.SocialUserResponse;

public interface FetchUserUseCase {
    UserResponse findUserByEmail(String email);
    UserResponse findByProviderId(String providerId);
    SocialUserResponse findKakaoUser(String accessToken);
}
