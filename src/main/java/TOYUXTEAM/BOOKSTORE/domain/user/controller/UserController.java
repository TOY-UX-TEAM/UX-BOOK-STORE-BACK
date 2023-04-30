package TOYUXTEAM.BOOKSTORE.domain.user.controller;


import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.JwtTokenRes;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.response.UserCountResponse;
import TOYUXTEAM.BOOKSTORE.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("user/signup")
    public void registerUser(@RequestBody RegisterUserReq registerUserReq){
        userService.register(registerUserReq);

    }

    @GetMapping("user/signin")
    public JwtTokenRes login(@RequestBody RegisterUserReq registerUserReq){
        JwtTokenRes token = userService.login(registerUserReq);
        return token;
    }

    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/calendar/{month}/{day}")
    public ResponseEntity<UserCountResponse> getCountForDate(@PathVariable("id") Long id,
                                                             @PathVariable("month") Long month, @PathVariable("day") Long day) {
        return ResponseEntity.ok().body(userService.findByDate(id, month, day));
    }
}
