package uk.codora.xvg.exception;

public class InvalidVergeAddressException extends RuntimeException {

    private static final long serialVersionUID = 7470441304460621759L;

    public InvalidVergeAddressException() {
        super();
    }

    public InvalidVergeAddressException(String message) {
        super(message);
    }

    public InvalidVergeAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVergeAddressException(Throwable cause) {
        super(cause);
    }

    protected InvalidVergeAddressException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
