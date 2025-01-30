package fast.campus.netplix3.entity.subscription;



import fast.campus.netplix3.subscription.UserSubscription;

import java.util.Optional;

public interface UserSubscriptionCustomRepository {
    Optional<UserSubscription> findByUserId(String userId);
}
