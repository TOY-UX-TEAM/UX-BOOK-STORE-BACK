package TOYUXTEAM.BOOKSTORE.domain.user.service;

import TOYUXTEAM.BOOKSTORE.domain.user.dto.JwtTokenRes;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.response.UserCountResponse;

public interface UserService {

    void register(RegisterUserReq registerUserReq);
    JwtTokenRes login(RegisterUserReq registerUserReq);
    UserCountResponse findByDate(Long id, Long month, Long day);
}
