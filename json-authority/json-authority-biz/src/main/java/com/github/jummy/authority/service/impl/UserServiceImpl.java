package com.github.jummy.authority.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jummy.authority.dao.UserMapper;
import com.github.jummy.authority.service.UserRoleService;
import com.github.jummy.authority.service.UserService;
import com.github.jummy.database.mybatis.conditions.Wraps;
import com.github.jummy.entity.auth.User;
import com.github.jummy.utils.ArgumentAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.jummy.common.constant.BizConstant.DEF_PASSWORD;

/**
 * @author: jummy
 * @create_date: 19/09/2021 5:48 下午
 * @desc:
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean check(Long id, String account) {
        return count(Wraps.<User>lbQ().eq(User::getAccount, account).ne(User::getId, id)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(User user) {
        ArgumentAssert.isFalse(check(null, user.getAccount()), "账号{}已经存在", user.getAccount());
        if (StrUtil.isEmpty(user.getPassword())) {
            user.setPassword(DEF_PASSWORD);
        }
        user.setPassword(SecureUtil.sha256(user.getPassword()));
        super.save(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initUser(User user) {
        ArgumentAssert.isFalse(check(null, user.getAccount()), "账号{}已经存在", user.getAccount());
        this.saveUser(user);
        return userRoleService.initAdmin(user.getId());
    }
}
