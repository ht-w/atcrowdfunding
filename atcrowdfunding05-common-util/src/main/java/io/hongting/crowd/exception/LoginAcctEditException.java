package io.hongting.crowd.exception;

/**
 * @author hongting
 * @create 2021 09 29 12:28 PM
 */
public class LoginAcctEditException extends  RuntimeException{

    private static final long serialVersionUID =1L;

    public LoginAcctEditException() {
    }

    public LoginAcctEditException(String message) {
        super(message);
    }

    public LoginAcctEditException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctEditException(Throwable cause) {
        super(cause);
    }

    public LoginAcctEditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
