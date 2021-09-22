package com.github.jummy.database.mybatis.typehandler;

import org.apache.ibatis.type.Alias;

/**
 * @author: jummy
 * @create_date: 14/09/2021 10:40 上午
 * @desc:
 */
@Alias("leftLike")
public class LeftLikeTypeHandler extends BaseLikeTypeHandler {

    public LeftLikeTypeHandler() {
        super(true, false);
    }
}
