package fast.campus.netplix3.repository.token;

import fast.campus.netplix3.entity.token.TokenEntity;
import fast.campus.netplix3.token.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepository implements SearchTokenPort, InsertTokenPort, UpdateTokenPort {

    private final TokenJpaRepository tokenJpaRepository;


    @Override
    @Transactional
    public TokenPortResponse create(String userId, String accessToken, String refreshToken) {
        TokenEntity entity = TokenEntity.newTokenEntity(userId, accessToken, refreshToken);
        tokenJpaRepository.save(entity);
        return new TokenPortResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NetplixToken> findByUserId(String userId) {
        return tokenJpaRepository.findByUserId(userId)
                .map(TokenEntity::toDomain);
    }


    @Override
    @Transactional
    public void updateToken(String userId, String accessToken, String refreshToken) {
        Optional<TokenEntity> byUserId = tokenJpaRepository.findByUserId(userId);
        if (byUserId.isEmpty()) {
            throw new RuntimeException();
        }
        TokenEntity tokenEntity = byUserId.get();
        tokenEntity.updateToken(accessToken, refreshToken);
        tokenJpaRepository.save(tokenEntity);

    }
}
