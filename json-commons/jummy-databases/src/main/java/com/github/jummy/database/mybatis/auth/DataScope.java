package com.github.jummy.database.mybatis.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

/**
 * @author: jummy
 * @create_date: 13/09/2021 3:21 下午
 * @desc:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {
    /**
     * 限制范围的字段名称 （除个人外）
     */
    private String scopeName = "org_id";
    /**
     * 限制范围为个人时的字段名称
     */
    private String selfScopeName = "create_user";
    /**
     * 当前用户ID
     */
    private Long userId;

    /**
     * 具体的数据范围
     */
    private List<Long> orgIds;
}
