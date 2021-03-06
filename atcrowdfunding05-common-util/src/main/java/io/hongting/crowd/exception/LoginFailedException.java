package io.hongting.crowd.exception;

/**
 * @author hongting
 * @create 2021 09 27 3:39 PM
 */
public class LoginFailedException extends RuntimeException {

    private static final long serialVersionUID =1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
