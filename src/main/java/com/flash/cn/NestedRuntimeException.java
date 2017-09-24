package com.flash.cn;

/**
 * Nested Runtime Exception.
 *
 * @author kay
 * @version v1.0
 */
public abstract class NestedRuntimeException extends RuntimeException {

    /**
     * 默认构造器
     */
    public NestedRuntimeException() {
        super();
    }

    /**
     * 带有堆栈异常信息的构造器
     *
     * @param cause 堆栈信息
     */
    public NestedRuntimeException(Throwable cause) {
        super(cause);
    }
}
