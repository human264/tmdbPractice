package fast.campus.netplix3.token;

import fast.campus.netplix3.token.response.TokenResponse;

public interface CreateTokenUseCase {

    TokenResponse createNewToken(String userId);
}
