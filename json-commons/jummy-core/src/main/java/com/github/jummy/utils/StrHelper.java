package com.github.jummy.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author: jummy
 * @create_date: 13/09/2021 3:01 下午
 * @desc:
 */
@Slf4j
public class StrHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getOrDef(String val, String def) {
        return StrUtil.isEmpty(val) ? def : val;
    }
}
