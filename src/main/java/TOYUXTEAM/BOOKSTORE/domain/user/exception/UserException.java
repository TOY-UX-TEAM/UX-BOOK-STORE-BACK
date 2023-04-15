package TOYUXTEAM.BOOKSTORE.domain.user.exception;

import javax.persistence.EntityNotFoundException;

public class UserException extends EntityNotFoundException {

    public UserException(String message) {
        super(message);
    }
}