package io.hongting.crowd.exception;

/**
 * @author hongting
 * @create 2021 09 27 5:49 PM
 */
public class AccessForbiddenException extends  RuntimeException {

    private static final long serialVersionUID =1L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
