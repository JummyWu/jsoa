package com.github.jummy.database.mybatis.typehandler;

import org.apache.ibatis.type.Alias;

/**
 * @author: jummy
 * @create_date: 14/09/2021 10:41 上午
 * @desc:
 */
@Alias("rightLike")
public class RightLikeTypeHandler extends BaseLikeTypeHandler{

    public RightLikeTypeHandler(){
        super(false, true);
    }
}
