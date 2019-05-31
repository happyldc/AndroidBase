package com.happyldc.base.crash;

/***
 * 全局异常捕获时默认处理前拦截
 */
public interface ExceptionIntercept {
    /**
     *
     * @param t
     * @param e
     * @return true 拦截 后续不再处理
     */
    boolean uncaughtException(Thread t, Throwable e);
}
