package com.github.jummy.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jummy.entity.auth.User;
import org.springframework.stereotype.Repository;

/**
 * @author: jummy
 * @create_date: 18/09/2021 11:06 下午
 * @desc:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
