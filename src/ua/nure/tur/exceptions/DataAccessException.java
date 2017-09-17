package ua.nure.tur.exceptions;

public class DataAccessException extends Exception {

    private static final long serialVersionUID = 19791099622980701L;

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }
}
