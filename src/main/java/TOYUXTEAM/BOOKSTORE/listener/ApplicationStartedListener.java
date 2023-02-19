package TOYUXTEAM.BOOKSTORE.listener;

import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        setUpDataLoad();
    }
    @Transactional
    public void setUpDataLoad(){
        User user1 = User.builder()
                .id("user1")
                .email("gkfktkrh153@naver.com")
                .name("seungYong")
                .password("123123")
                .role("manager")
                .build();

        User user2 = User.builder()
                .id("user2")
                .email("heyri0603@naver.com")
                .name("suho")
                .password("123123")
                .role("manager")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
