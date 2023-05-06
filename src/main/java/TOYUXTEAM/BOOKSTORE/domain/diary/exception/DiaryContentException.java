package TOYUXTEAM.BOOKSTORE.domain.diary.exception;

import TOYUXTEAM.BOOKSTORE.global.exception.ErrorCode;
import TOYUXTEAM.BOOKSTORE.global.exception.baseException;
import org.springframework.http.HttpStatus;

public class DiaryContentException extends baseException {

    private static final ErrorCode errorCode = ErrorCode.DIARY_CONTENT_NOT_FOUND;

    public DiaryContentException(String message) {
        super(errorCode, HttpStatus.BAD_REQUEST, message);
    }
}