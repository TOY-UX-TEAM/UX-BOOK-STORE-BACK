package TOYUXTEAM.BOOKSTORE.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User { // UserDetails Interface를 구현한 클래스
    private final TOYUXTEAM.BOOKSTORE.domain.user.model.User user;



    public AccountContext(TOYUXTEAM.BOOKSTORE.domain.user.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getName(), user.getPassword(), authorities);
        this.user = user;
    }
    public TOYUXTEAM.BOOKSTORE.domain.user.model.User getUser() {
        return user;
    }
}
