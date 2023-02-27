package TOYUXTEAM.BOOKSTORE.domain.user.controller;


import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/user")
    public String user(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return "user";
    }
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }
    @PostMapping("/user")
    public void registerUser(@RequestBody RegisterUserReq registerUserReq){
        userService.register(registerUserReq);

    }
}
