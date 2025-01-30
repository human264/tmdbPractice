package fast.campus.netplix3.token;

public interface UpdateTokenPort {
    void updateToken(String userId, String accessToken, String refreshToken);
}
