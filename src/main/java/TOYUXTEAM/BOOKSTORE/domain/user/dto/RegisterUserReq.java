package TOYUXTEAM.BOOKSTORE.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserReq {

    private String id;
    private String username;
    private String password;
    private String email;
    private String roles;
}
