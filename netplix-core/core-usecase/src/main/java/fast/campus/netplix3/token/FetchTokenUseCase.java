package fast.campus.netplix3.token;

import fast.campus.netplix3.user.command.UserResponse;

public interface FetchTokenUseCase {
    Boolean validateToken(String accessToken);
    String getTokenFromKakao(String code);
    UserResponse findUserByAccessToken(String accessToken);

    UserResponse findKakaoUser(String accessTokenFromKakao);
}
