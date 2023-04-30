package TOYUXTEAM.BOOKSTORE.domain.user.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserSearchCond {

    private final Long userId;
    private LocalDate localDate;

    public UserSearchCond(Long userId) {
        this.userId = userId;
    }

    public UserSearchCond(Long userId, LocalDate localDate) {
        this.userId = userId;
        this.localDate = localDate;
    }
}