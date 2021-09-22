package com.github.jummy.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jummy.entity.auth.UserRole;

/**
 * @author: jummy
 * @create_date: 19/09/2021 6:07 下午
 * @desc:
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 初始化超级管理员角色 权限
     *
     * @param userId 用户id
     * @return 是否正确
     */
    boolean initAdmin(Long userId);
}
