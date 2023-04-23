package TOYUXTEAM.BOOKSTORE.global.exception;

import org.springframework.http.HttpStatus;

public abstract class baseException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    protected baseException(String errorCode, HttpStatus httpStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
