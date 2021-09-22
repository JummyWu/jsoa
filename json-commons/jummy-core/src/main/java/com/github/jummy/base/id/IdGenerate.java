package com.github.jummy.base.id;

import java.io.Serializable;

/**
 * @author: jummy
 * @create_date: 13/09/2021 2:50 下午
 * @desc:
 */
@FunctionalInterface
public interface IdGenerate<T extends Serializable> {

    T generate();
}
