package fast.campus.netplix3.token;

import fast.campus.netplix3.token.response.TokenResponse;

public interface UpdateTokenUseCase {
    String upsertToken(String providerId);

//    TokenResponse updateNewToken(String userId);
//
//    TokenResponse upsertToken(String userId);
//
//    TokenResponse reissueToken(String accessToken, String refreshToken);
}
