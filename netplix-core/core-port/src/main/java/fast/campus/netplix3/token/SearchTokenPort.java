package fast.campus.netplix3.token;


import java.util.Optional;

public interface SearchTokenPort {
    Optional<NetplixToken> findByUserId(String userId);
}
