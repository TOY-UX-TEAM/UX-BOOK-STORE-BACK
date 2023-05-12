package TOYUXTEAM.BOOKSTORE.domain.user.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterUserReq {

    @Builder
    public RegisterUserReq(String id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
}
