package com.github.jummy.exception;

import com.github.jummy.exception.code.ExceptionCode;

/**
 * @author: jummy
 * @create_date: 18/09/2021 10:55 下午
 * @desc:
 */
public class ArgumentException extends BaseUncheckedException{

    private static final long serialVersionUID = -3843907364558373817L;

    public ArgumentException(Throwable cause) {
        super(cause);
    }

    public ArgumentException(String message) {
        super(ExceptionCode.BASE_VALID_PARAM.getCode(), message);
    }

    public ArgumentException(String message, Throwable cause) {
        super(ExceptionCode.BASE_VALID_PARAM.getCode(), message, cause);
    }

    public ArgumentException(final String format, Object... args) {
        super(ExceptionCode.BASE_VALID_PARAM.getCode(), format, args);
    }

    @Override
    public String toString() {
        return "ArgumentException [message=" + getMessage() + ", code=" + getCode() + "]";
    }

}
