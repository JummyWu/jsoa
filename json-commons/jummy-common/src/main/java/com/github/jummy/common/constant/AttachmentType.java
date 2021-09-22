package com.github.jummy.common.constant;

import com.github.jummy.exception.code.ExceptionCode;
import com.github.jummy.utils.BizAssert;

import java.util.Arrays;

/**
 * @author: jummy
 * @create_date: 15/09/2021 9:48 上午
 * @desc:
 */
public class AttachmentType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final String[] ALL_TYPES = {
            Authority.APPLICATION_LOGO_URL,
            Authority.APPLICATION_TITLE_ICON,
    };

    private AttachmentType() {
    }

    public static boolean assertType(String type) {
        boolean flag = Arrays.asList(ALL_TYPES).contains(type);
        BizAssert.isTrue(flag, ExceptionCode.BAD_GATEWAY);
        return flag;
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author oyx
     */
    interface Authority {
        /**
         * 权限管理系统中的应用表中的logo
         */
        String APPLICATION_LOGO_URL = "auth-application-logo";
        /**
         * 权限管理系统中的应用表中的应用标题图标
         */
        String APPLICATION_TITLE_ICON = "auth-application-titleIcon";
    }

    /**
     * 文件 业务类型定义
     */
    interface File {

    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msgs {

    }

}
