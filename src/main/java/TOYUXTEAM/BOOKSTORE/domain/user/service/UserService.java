package TOYUXTEAM.BOOKSTORE.domain.user.service;


import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(RegisterUserReq registerUserReq) {
        User user = User.builder()
                .id(registerUserReq.getId())
                .name(registerUserReq.getName())
                .email(registerUserReq.getEmail())
                .password(registerUserReq.getPassword())
                .role(registerUserReq.getRole())
                .build();
        userRepository.save(user);
    }
}
