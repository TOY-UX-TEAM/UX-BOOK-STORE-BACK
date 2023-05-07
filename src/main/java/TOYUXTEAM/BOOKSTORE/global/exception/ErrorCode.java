package TOYUXTEAM.BOOKSTORE.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(404, "ACCOUNT-001", "회원이 존재하지 않는 경우"),
    MAXSIZE_OVER(400, "ACCOUNT-002", String.format("파일은 최대 %sMB 크기까지 입력할 수 있습니다.", 3)),
    DIARY_NOT_FOUND(404, "DIARY-001", "일기가 존재하지 않는 경우"),
    USER_EXIST(400, "ACCOUNT-003", "해당 아이디를 가진 회원이 이미 존재합니다");

    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
}
