package TOYUXTEAM.BOOKSTORE.domain.diary.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.ErrorCode;
import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class DiaryException extends baseException {

    private static final ErrorCode errorCode = ErrorCode.DIARY_NOT_FOUND;

    public DiaryException(String message) {
        super(errorCode, HttpStatus.BAD_REQUEST, message);
    }
}
