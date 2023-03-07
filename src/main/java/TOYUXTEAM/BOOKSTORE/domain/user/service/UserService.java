package TOYUXTEAM.BOOKSTORE.domain.user.service;

import TOYUXTEAM.BOOKSTORE.domain.user.dto.JwtTokenRes;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.RegisterUserReq;

public interface UserService {

    void register(RegisterUserReq registerUserReq);
    JwtTokenRes login(RegisterUserReq registerUserReq);
}
