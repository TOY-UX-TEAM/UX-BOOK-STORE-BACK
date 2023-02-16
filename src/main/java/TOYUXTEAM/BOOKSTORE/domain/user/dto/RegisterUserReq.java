package TOYUXTEAM.BOOKSTORE.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserReq {


    private String name;
    private String password;
    private String email;
    private String role;
}
