package fast.campus.netplix3.user;

import fast.campus.netplix3.exception.UserException;
import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.command.UserResponse;
import fast.campus.netplix3.user.response.SocialUserResponse;
import fast.campus.netplix3.user.response.UserRegisterResponse;
import fast.campus.netplix3.user.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

    private final FetchUserPort fetchUserPort;
    private final InsertUserPort insertUserPort;
    private final KakaoUserPort kakaoUserPort;



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
    public UserResponse findByProviderId(String providerId) {
        return fetchUserPort.findByProviderId(providerId)
                .map(it -> UserResponse.builder()
                        .providerId(it.getProviderId())
                        .provider(it.getProvider())
                        .username(it.getUsername())
                        .build()
                ).orElse(null);
    }

    @Override
    public SocialUserResponse findKakaoUser(String accessToken) {
        NetplixUser userFromKakao = kakaoUserPort.findUserFromKakao(accessToken);
        return new SocialUserResponse(
                userFromKakao.getUsername(), "kakao", userFromKakao.getProviderId()
        );
    }

    @Override
    public UserRegistrationResponse register(UserRegistrationCommand command) {
        String email = command.email();

        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new UserException.UserAlreadyExistException();
        }

        UserPortResponse response = insertUserPort.create(
                CreateUser.builder()
                        .username(command.username())
                        .encryptedPassword(command.encryptedPassword())
                        .email(command.email())
                        .phone(command.phone())
                        .build()
        );

        return new UserRegistrationResponse(response.getUsername(), response.getEmail(), response.getPhone());

    }

    @Override
    public UserRegistrationResponse registerSocialUser(String username, String provider, String providerId) {
        Optional<UserPortResponse> byProviderId = fetchUserPort.findByProviderId(providerId);
        if (byProviderId.isPresent()) {
            return null;
        }
        UserPortResponse socialUser = insertUserPort.createSocialUser(username, provider, providerId);

        return new UserRegistrationResponse(socialUser.getUsername(),null,null);
    }
}
