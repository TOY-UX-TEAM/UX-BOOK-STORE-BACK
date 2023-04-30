package TOYUXTEAM.BOOKSTORE.global.exception;


import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> UserHandleException(UserException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("[exceptionHandle] ex", e);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(DiaryException.class)
    public ResponseEntity<ErrorResponse> DiaryHandleException(DiaryException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("[exceptionHandle] ex", e);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ErrorResponse> FileHandleException(FileSizeLimitExceededException e) {
        ErrorCode errorCode = ErrorCode.MAXSIZE_OVER;
        log.error("[exceptionHandle] ex", e);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getDescription());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
