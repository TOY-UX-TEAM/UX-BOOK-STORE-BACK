package TOYUXTEAM.BOOKSTORE.domain.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCountResponse {

    private Long userId;
    private Integer count;

    @QueryProjection
    public UserCountResponse(Long userId, Integer count) {
        this.userId = userId;
        this.count = count;
    }
}
