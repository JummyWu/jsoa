package com.github.jummy.exception;

/**
 * @author: jummy
 * @create_date: 13/09/2021 10:21 上午
 * @desc:
 */
public class BaseUncheckedException extends RuntimeException implements BaseException {
    private static final long serialVersionUID = -778887391066124051L;

    /**
     * 异常信息
     */
    protected String message;

    /**
     * 具体异常码
     */
    protected int code;

    public BaseUncheckedException(Throwable cause) {
        super(cause);
    }

    public BaseUncheckedException(final int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseUncheckedException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BaseUncheckedException(int code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
        this.message = String.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public int getCode() {
        return code;
    }
}
