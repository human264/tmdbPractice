package fast.campus.netplix3.user;

public interface KakaoUserPort {
    NetplixUser findUserFromKakao(String accessToken);
}
