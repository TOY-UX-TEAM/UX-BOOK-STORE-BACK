package TOYUXTEAM.BOOKSTORE.domain.user.service;


import TOYUXTEAM.BOOKSTORE.domain.user.dto.JwtTokenRes;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import TOYUXTEAM.BOOKSTORE.security.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    @Override
    public void register(RegisterUserReq registerUserReq) {
        User user = User.builder()
                .id(registerUserReq.getId())
                .name(registerUserReq.getName())
                .email(registerUserReq.getEmail())
                .password(passwordEncoder.encode(registerUserReq.getPassword()))
                .roles(Collections.singletonList(registerUserReq.getRole()))
                .build();

        userRepository.save(user);
    }

    public JwtTokenRes login(RegisterUserReq registerUserReq) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(registerUserReq.getId(), registerUserReq.getPassword());
        // 인증객체 생성 (authenticated 값은 false)
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 실제 인증 과정으로 authenticate실행 시 CustomUserDetailsService의 loadByUserName실행
        JwtTokenRes jwtTokenRes = tokenProvider.generateToken(authenticate, registerUserReq.getRole());
        return jwtTokenRes;
    }
}
