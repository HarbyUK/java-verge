package uk.codora.xvg.exception;

public class JsonRpcResponseException extends RuntimeException {

    private static final long serialVersionUID = 9028445949832230258L;

    public JsonRpcResponseException() {
        super();
    }

    public JsonRpcResponseException(String message) {
        super(message);
    }

    public JsonRpcResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonRpcResponseException(Throwable cause) {
        super(cause);
    }

    protected JsonRpcResponseException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
