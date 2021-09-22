package com.github.jummy.cache.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.jummy.common.converter.EnumDeserializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.github.jummy.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * @author: jummy
 * @create_date: 15/09/2021 10:05 上午
 * @desc:
 */
public class RedisObjectSerializer  extends Jackson2JsonRedisSerializer<Object> {

    public RedisObjectSerializer() {
        super(Object.class);
        ObjectMapper om = new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                //日期格式
                .setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));
        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(Enum.class, EnumDeserializer.INSTANCE)
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        om.registerModule(simpleModule);
        this.setObjectMapper(om);
    }
}
