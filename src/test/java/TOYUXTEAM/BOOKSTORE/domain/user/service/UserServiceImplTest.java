package TOYUXTEAM.BOOKSTORE.domain.user.service;

import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserExistException;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("중복 회원가입 예외처리")
    void t1(){
        RegisterUserReq userReq = RegisterUserReq.builder()
                .id("gkfktkrh153")
                .email("gkfktkrh153@naver.com")
                .username("user1")
                .password("1234")
                .roles("ROLE_USER")
                .build();
        assertThrows(UserExistException.class, ()-> userService.register(userReq));

    }

}