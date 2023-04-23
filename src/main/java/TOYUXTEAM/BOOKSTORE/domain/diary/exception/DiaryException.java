package TOYUXTEAM.BOOKSTORE.domain.diary.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class DiaryException extends baseException {

    private static final String code = "d0001";

    public DiaryException(String message) {
        super(code, HttpStatus.BAD_REQUEST, message);
    }
}
