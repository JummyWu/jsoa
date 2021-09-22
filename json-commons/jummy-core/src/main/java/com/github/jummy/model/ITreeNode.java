package com.github.jummy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jummy
 * @create_date: 13/09/2021 2:58 下午
 * @desc:
 */
public interface ITreeNode<T, I extends Serializable> {
    /**
     * 获取id
     *
     * @return
     */
    I getId();

    /**
     * 获取父id
     *
     * @return
     */
    I getParentId();

    /**
     * 获取子类
     *
     * @return
     */
    List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children
     */
    void setChildren(List<T> children);

    /**
     * 初始化子类
     */
    default void initChildren() {
        if (getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
    }
}
