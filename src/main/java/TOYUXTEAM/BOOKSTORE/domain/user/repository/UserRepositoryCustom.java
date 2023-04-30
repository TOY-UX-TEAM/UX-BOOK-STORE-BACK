package TOYUXTEAM.BOOKSTORE.domain.user.repository;

import TOYUXTEAM.BOOKSTORE.domain.user.dto.request.UserSearchCond;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.response.UserCountResponse;

public interface UserRepositoryCustom {
    UserCountResponse findByDate(UserSearchCond cond);
}
