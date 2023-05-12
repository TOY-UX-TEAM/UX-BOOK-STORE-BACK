package TOYUXTEAM.BOOKSTORE.domain.user.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.ErrorCode;
import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class UserExistException extends baseException {

    private static final ErrorCode code = ErrorCode.USER_NOT_FOUND;

    public UserExistException(String message) {
        super(code, HttpStatus.BAD_REQUEST, message);
    }
}