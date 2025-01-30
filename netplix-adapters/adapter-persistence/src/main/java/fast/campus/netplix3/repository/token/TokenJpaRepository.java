package fast.campus.netplix3.repository.token;

import fast.campus.netplix3.entity.token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, String>, TokenCustomRepository {
    Optional<TokenEntity> findByUserId(String userId);
}
