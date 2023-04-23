package TOYUXTEAM.BOOKSTORE.domain.user.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class UserException extends baseException {

    private static final String code = "u0001";

    public UserException(String message) {
        super(code, HttpStatus.BAD_REQUEST, message);
    }
}