package com.github.jummy.base.service.impl;

import com.github.jummy.base.mapper.SuperMapper;
import com.github.jummy.base.service.SuperCacheService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: jummy
 * @create_date: 18/09/2021 12:14 上午
 * @desc:
 */
public abstract class SuperCacheServiceImpl<M extends SuperMapper<T>, T> extends SuperServiceImpl<M, T> implements SuperCacheService<T> {

    @Autowired
//    protected CacheRepository cacheRepository;

    protected static final int MAX_BATCH_KEY_SIZE = 20;

}
