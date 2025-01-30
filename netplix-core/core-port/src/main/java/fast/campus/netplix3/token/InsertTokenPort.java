package fast.campus.netplix3.token;


public interface InsertTokenPort {
    TokenPortResponse create(String userId, String accessToken, String refreshToken);
}
