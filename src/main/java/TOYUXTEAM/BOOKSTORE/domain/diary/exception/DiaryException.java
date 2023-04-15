package TOYUXTEAM.BOOKSTORE.domain.diary.exception;

import javax.persistence.EntityNotFoundException;

public class DiaryException extends EntityNotFoundException {

    public DiaryException(String message) {
        super(message);
    }
}
