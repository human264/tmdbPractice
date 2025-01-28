package fast.campus.netplix3.user;

import fast.campus.netplix3.NetplixUser;
import fast.campus.netplix3.exception.UserException;
import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.command.UserResponse;
import fast.campus.netplix3.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

    private final FetchUserPort fetchUserPort;
    private final InsertUserPort insertUserPort;


    @Override
    public UserResponse findUserByEmail(String email) {
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }
        UserPortResponse userPortResponse = byEmail.get();
        return UserResponse.builder()
                .userId(userPortResponse.getUserId())
                .email(userPortResponse.getEmail())
                .password(userPortResponse.getPassword())
                .phone(userPortResponse.getPhone())
                .role(userPortResponse.getRole())
                .username(userPortResponse.getUsername())
                .build();
    }

    @Override
    public UserRegisterResponse register(UserRegistrationCommand command) {
        String email = command.getEmail();

        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new UserException.UserAlreadyExistException();
        }

        UserPortResponse response = insertUserPort.create(
                CreateUser.builder()
                        .username(command.getUsername())
                        .encryptedPassword(command.getEncryptedPassword())
                        .email(command.getEmail())
                        .phone(command.getPhone())
                        .build()
        );

        return new UserRegisterResponse(response.getUsername(), response.getEmail(), response.getPhone());

    }
}
