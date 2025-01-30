package fast.campus.netplix3.entity.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionJpaRepository extends JpaRepository<UserSubscriptionEntity, String>, UserSubscriptionCustomRepository {
}
