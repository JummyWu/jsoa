package com.github.jummy.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.jummy.utils.StrPool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: jummy
 * @create_date: 15/09/2021 10:11 上午
 * @desc:
 */
@Slf4j
@EnableCaching
@Import(RedisAutoConfigure.class)
public class CacheAutoConfigure {

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(StrPool.COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(StrPool.COLON);
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }
}
