package fast.campus.netplix3.token;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class NetplixToken {
    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessTokenExpireAt;
    private final LocalDateTime refreshTokenExpireAt;

}
