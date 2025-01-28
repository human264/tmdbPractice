package fast.campus.netplix3.user;

import fast.campus.netplix3.user.command.UserResponse;

public interface FetchUserUseCase {
    UserResponse findUserByEmail(String email);

}
