package TOYUXTEAM.BOOKSTORE.security.provider;

import TOYUXTEAM.BOOKSTORE.security.service.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String password = (String)authentication.getCredentials(); // 사용자 입력값

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(userId);
        if(!passwordEncoder.matches(password, accountContext.getUser().getPassword())) // 사용자 입력값 , DB값
        {
            throw new BadCredentialsException("BadCredentialsException");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getUser(), null, accountContext.getAuthorities());
        return authenticationToken;
        // 최종 인증 성공
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom( authentication );
    }
}
