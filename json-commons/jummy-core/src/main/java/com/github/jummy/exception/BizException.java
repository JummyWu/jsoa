package com.github.jummy.exception;

import com.github.jummy.exception.code.BaseExceptionCode;

/**
 * @author: jummy
 * @create_date: 13/09/2021 10:12 上午
 * @desc:
 */
public class BizException extends BaseUncheckedException  {
    private static final long serialVersionUID = -3843907364558373817L;

    public BizException(String message) {
        super(-1, message);
    }

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(int code, String message, Object... args) {
        super(code, message, args);
    }

    /**
     * 实例化异常
     *
     * @param code    自定义异常编码
     * @param message 自定义异常消息
     * @param args    已定义异常参数
     * @return
     */
    public static BizException wrap(int code, String message, Object... args) {
        return new BizException(code, message, args);
    }

    public static BizException wrap(String message, Object... args) {
        return new BizException(-1, message, args);
    }

    public static BizException validFail(String message, Object... args) {
        return new BizException(-9, message, args);
    }

    public static BizException wrap(BaseExceptionCode ex) {
        return new BizException(ex.getCode(), ex.getMsg());
    }

    @Override
    public String toString() {
        return "BizException [message=" + message + ", code=" + code + "]";
    }
}
