package fast.campus.netplix3.repository.token;

import fast.campus.netplix3.entity.token.TokenEntity;

import java.util.Optional;

public interface TokenCustomRepository {
    Optional<TokenEntity> findByToken(String userId);
}


