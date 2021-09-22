package com.github.jummy.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jummy.entity.auth.User;

/**
 * @author: jummy
 * @create_date: 17/09/2021 11:37 下午
 * @desc:
 */
public interface UserService extends IService<User> {


    /**
     * 检测账号是否存在
     *
     * @param id      id
     * @param account 账号
     * @return true 表示存在
     */
    boolean check(Long id, String account);

    /**
     * 保存
     *
     * @param user 用户
     * @return 用户
     */
    User saveUser(User user);

    /**
     * 初始化用户
     *
     * @param user 用户
     * @return 是否成功
     */
    boolean initUser(User user);


}
