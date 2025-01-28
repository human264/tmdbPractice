package fast.campus.netplix3.repository.user;


import fast.campus.netplix3.entity.user.UserEntity;
import fast.campus.netplix3.user.FetchUserPort;
import fast.campus.netplix3.user.UserPortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserPortResponse> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        if(byEmail.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                UserPortResponse.builder()
                        .userId(byEmail.get().getUserId())
                        .password(byEmail.get().getPassword())
                        .username(byEmail.get().getUsername())
                        .email(byEmail.get().getEmail())
                        .phone(byEmail.get().getPhone())
                        .build()
        );
    }

}
