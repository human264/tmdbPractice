package fast.campus.netplix3.repository.user.social;

import fast.campus.netplix3.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialUserJpaRepository extends JpaRepository<SocialUserEntity, String> {
    Optional<SocialUserEntity> findByProviderId(String providerId);

}
