package com.github.jummy.function;

/**
 * @author: jummy
 * @create_date: 13/09/2021 3:12 下午
 * @desc:
 */
public interface CheckedFunction<T, R> {
    /**
     * 执行
     *
     * @param t 入参
     * @return R 出参
     * @throws Exception
     */
    R apply(T t) throws Exception;
}
