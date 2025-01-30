package fast.campus.netplix3.auth;

import fast.campus.netplix3.token.*;
import fast.campus.netplix3.token.response.TokenResponse;
import fast.campus.netplix3.user.FetchUserUseCase;
import fast.campus.netplix3.user.KakaoUserPort;
import fast.campus.netplix3.user.NetplixUser;
import fast.campus.netplix3.user.command.UserResponse;
import fast.campus.netplix3.user.response.SocialUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expire.access-token}")
    private int accessTokenExpireHour;

    @Value("${jwt.expire.refresh-token}")
    private int refreshTokenExpireHour;

    private final SearchTokenPort searchTokenPort;
    private final InsertTokenPort insertTokenPort;
    private final UpdateTokenPort updateTokenPort;
    private final FetchUserUseCase fetchUserUseCase;
    private final KakaoTokenPort kakaoTokenPort;
    private final KakaoUserPort kakaoUserPort;

    @Override
    public UserResponse findUserByAccessToken(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Object userId = claims.get("userId");

        if (ObjectUtils.isEmpty(userId)) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        return fetchUserUseCase.findByProviderId(userId.toString());
    }

    @Override
    public UserResponse findKakaoUser(String accessToken) {
        NetplixUser userFromKakao = kakaoUserPort.findUserFromKakao(accessToken);
        return UserResponse.toUserResponse(userFromKakao);
    }

    @Override
    public Boolean validateToken(String accessToken) {
        Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken);

        return true;
    }

    @Override
    public String getTokenFromKakao(String code) {
        return kakaoTokenPort.getAccessTokenByCode(code);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    @Override
    public TokenResponse createNewToken(String userId) {
        String accessToken = getToken(userId, Duration.ofHours(accessTokenExpireHour));
        String refreshToken = getToken(userId, Duration.ofHours(refreshTokenExpireHour));
        TokenPortResponse netplixToken = insertTokenPort.create(userId, accessToken, refreshToken);
        return TokenResponse.builder()
                .accessToken(netplixToken.getAccessToken())
                .refreshToken(netplixToken.getRefreshToken())
                .build();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getToken(String userId, Duration expireAt) {
        Date now = new Date();
        Instant instant = now.toInstant();

        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(Date.from(instant.plus(expireAt)))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String upsertToken(String providerId) {
        NetplixToken byUserId = searchTokenPort.findByUserId(providerId).get();
        String accessToken = getToken(providerId, Duration.ofHours(3));
        String refreshToken = getToken(providerId, Duration.ofHours(24));

        if (byUserId == null) {
            insertTokenPort.create(providerId, accessToken, refreshToken);
        } else {
            updateTokenPort.updateToken(providerId, accessToken, refreshToken);
        }
        return accessToken;
    }
}

