package TOYUXTEAM.BOOKSTORE.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtTokenRes {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}
