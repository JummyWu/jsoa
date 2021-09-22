package com.github.jummy.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.jummy.base.R;
import com.github.jummy.base.mapper.SuperMapper;
import com.github.jummy.base.service.SuperService;
import com.github.jummy.exception.BizException;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

import static com.github.jummy.exception.code.ExceptionCode.SERVICE_MAPPER_ERROR;


/**
 * @author: jummy
 * @create_date: 18/09/2021 12:14 上午
 * @desc:
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

    private Class<T> entityClass = null;

    public SuperMapper getSuperMapper() {
        if (baseMapper instanceof SuperMapper) {
            return baseMapper;
        }
        throw BizException.wrap(SERVICE_MAPPER_ERROR);
    }

    @Override
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return this.entityClass;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(T model) {
        R<Boolean> result = handlerSave(model);
        if (result.getDefExec()) {
            return super.save(model);
        }
        return result.getData();
    }

    /**
     * 处理新增相关处理
     *
     * @param model 实体
     * @return 是否成功
     */
    protected R<Boolean> handlerSave(T model) {
        return R.successDef();
    }

    /**
     * 处理修改相关处理
     *
     * @param model 实体
     * @return 是否成功
     */
    protected R<Boolean> handlerUpdateAllById(T model) {
        return R.successDef();
    }

    /**
     * 处理修改相关处理
     *
     * @param model 实体
     * @return 是否成功
     */
    protected R<Boolean> handlerUpdateById(T model) {
        return R.successDef();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllById(T model) {
        R<Boolean> result = handlerUpdateAllById(model);
        if (result.getDefExec()) {
            return SqlHelper.retBool(getSuperMapper().updateAllById(model));
        }
        return result.getData();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(T model) {
        R<Boolean> result = handlerUpdateById(model);
        if (result.getDefExec()) {
            return super.updateById(model);
        }
        return result.getData();
    }
}
