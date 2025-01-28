package fast.campus.netplix3.user;

import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.response.UserRegisterResponse;

public interface RegisterUserUseCase {

    UserRegisterResponse register(UserRegistrationCommand command);



}
