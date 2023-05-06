package TOYUXTEAM.BOOKSTORE.domain.user.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.ErrorCode;
import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class UserNotMatchException extends baseException {

    private static final ErrorCode code = ErrorCode.USER_NOT_MATCH;

    public UserNotMatchException(String message) {
        super(code, HttpStatus.FORBIDDEN, message);
    }
}