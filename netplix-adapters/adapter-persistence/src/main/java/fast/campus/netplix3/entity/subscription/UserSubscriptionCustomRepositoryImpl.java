package fast.campus.netplix3.entity.subscription;

import com.querydsl.jpa.impl.JPAQueryFactory;

import fast.campus.netplix3.subscription.UserSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionCustomRepositoryImpl implements UserSubscriptionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<UserSubscription> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(QUserSubscriptionEntity.userSubscriptionEntity)
                .fetch()
                .stream()
                .findFirst()
                .map(UserSubscriptionEntity::toDomain);
    }
}
