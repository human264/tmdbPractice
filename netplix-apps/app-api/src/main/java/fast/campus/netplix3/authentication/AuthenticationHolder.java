package fast.campus.netplix3.authentication;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AuthenticationHolder {
    Optional<Authentication> getAuthentication();
    void setAuthentication(Authentication authentication);

}
