package com.github.jummy.base;

import com.github.jummy.utils.MapHelper;

import java.util.Arrays;
import java.util.Map;

/**
 * @author: jummy
 * @create_date: 14/09/2021 10:27 上午
 * @desc:
 */
public interface BaseEnum {
    static Map<String, String> getMap(BaseEnum[] list) {
        return MapHelper.uniqueIndex(Arrays.asList(list), BaseEnum :: getCode, BaseEnum :: getDesc);
    }

    /**
     * 编码重写
     *
     * @return
     */
    default  String getCode() {
        return toString();
    }

    /**
     * 描述
     *
     * @return
     */
    String getDesc();
}
