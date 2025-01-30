package fast.campus.netplix3.repository.user;


import fast.campus.netplix3.entity.user.SocialUserEntity;
import fast.campus.netplix3.entity.user.UserEntity;
import fast.campus.netplix3.repository.user.social.SocialUserJpaRepository;
import fast.campus.netplix3.user.CreateUser;
import fast.campus.netplix3.user.FetchUserPort;
import fast.campus.netplix3.user.InsertUserPort;
import fast.campus.netplix3.user.UserPortResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort, InsertUserPort {

    private final UserJpaRepository userJpaRepository;
    private final SocialUserJpaRepository socialUserJpaRepository;

    @Override
    @Transactional
    public Optional<UserPortResponse> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
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

    @Override
    @Transactional
    public Optional<UserPortResponse> findByProviderId(String providerId) {
        Optional<SocialUserEntity> byProviderId = socialUserJpaRepository.findByProviderId(providerId);
        if (byProviderId.isEmpty()) {
            return Optional.empty();
        }
        SocialUserEntity socialUserEntity = byProviderId.get();

        return Optional.of(UserPortResponse.builder()
                .provider(socialUserEntity.getProvider())
                .providerId(socialUserEntity.getProviderId())
                .username(socialUserEntity.getUsername())
                .build()
        );
    }

    @Override
    @Transactional
    public UserPortResponse create(CreateUser user) {
        UserEntity userENtity = new UserEntity(user.getUsername(), user.getEncryptedPassword(), user.getEmail(), user.getPhone());
        UserEntity save = userJpaRepository.save(userENtity);
        return UserPortResponse.builder()
                .userId(save.getUserId())
                .username(save.getUsername())
                .password(save.getPassword())
                .email(save.getEmail())
                .phone(save.getPhone())
                .build();
    }

    @Override
    @Transactional
    public UserPortResponse createSocialUser(String username, String provider, String providerId) {
        SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
        socialUserJpaRepository.save(socialUserEntity);
        return UserPortResponse.builder()
                .provider(socialUserEntity.getProvider())
                .providerId(socialUserEntity.getProviderId())
                .username(socialUserEntity.getUsername())
                .build();
    }

}
