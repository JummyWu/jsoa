package com.github.jummy.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jummy.authority.dao.RoleMapper;
import com.github.jummy.authority.dao.UserRoleMapper;
import com.github.jummy.authority.service.UserRoleService;
import com.github.jummy.database.mybatis.conditions.Wraps;
import com.github.jummy.entity.auth.Role;
import com.github.jummy.entity.auth.UserRole;
import com.github.jummy.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: jummy
 * @create_date: 19/09/2021 6:07 下午
 * @desc:
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initAdmin(Long userId) {
        Role role = roleMapper.selectOne(Wraps.<Role>lbQ().eq(Role::getCode, "SUPER_ADMIN"));
        if (role == null) {
            throw BizException.wrap("初始化用户角色失败, 无法查询到内置角色:%s", "SUPER_ADMIN");
        }
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(role.getId())
                .build();

        return super.save(userRole);
    }
}
