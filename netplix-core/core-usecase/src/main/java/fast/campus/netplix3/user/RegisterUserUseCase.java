package fast.campus.netplix3.user;

import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.response.UserRegisterResponse;
import fast.campus.netplix3.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {

    UserRegistrationResponse register(UserRegistrationCommand command);
    UserRegistrationResponse registerSocialUser(String username, String provider, String providerId);


}
