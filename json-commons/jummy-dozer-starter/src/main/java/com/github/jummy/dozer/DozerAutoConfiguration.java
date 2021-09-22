package com.github.jummy.dozer;

import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * @author: jummy
 * @create_date: 15/09/2021 11:01 上午
 * @desc:
 */
public class DozerAutoConfiguration {
    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        return new DozerUtils(mapper);
    }
}
