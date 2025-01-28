package fast.campus.netplix3.authentication;

import java.util.Optional;

public interface RequestedByProvider {
    Optional<String> getRequestedBy();

}
