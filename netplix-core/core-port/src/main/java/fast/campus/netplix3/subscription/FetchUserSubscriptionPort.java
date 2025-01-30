package fast.campus.netplix3.subscription;

import java.util.Optional;

public interface FetchUserSubscriptionPort {
    Optional<UserSubscription> findByUserId(String userId);
}
