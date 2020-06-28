package uk.codora.xvg.exception;

public class VergeConfigurationFileException extends RuntimeException {

    private static final long serialVersionUID = -4336388761748496871L;

    public VergeConfigurationFileException() {
        super();
    }

    public VergeConfigurationFileException(String message) {
        super(message);
    }

    public VergeConfigurationFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public VergeConfigurationFileException(Throwable cause) {
        super(cause);
    }

    protected VergeConfigurationFileException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
