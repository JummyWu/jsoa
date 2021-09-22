package com.github.jummy.utils;

import com.github.jummy.model.ITreeNode;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: jummy
 * @create_date: 13/09/2021 2:59 下午
 * @desc:
 */
public class TreeUtil {
    public static <T extends ITreeNode<T, ? extends Serializable>> List<T> build(List<T> treeNodes) {
        if (CollectionUtils.isEmpty(treeNodes)) {
            return treeNodes;
        }
        //记录自己是自己的父节点的id集合
        List<Serializable> selfIdEqSelfParent = new ArrayList<>();
        // 为每一个节点找到子节点集合
        for (T parent : treeNodes) {
            Serializable id = parent.getId();
            for (T children : treeNodes) {
                if (parent != children) {
                    //parent != children 这个来判断自己的孩子不允许是自己，因为有时候，根节点的parent会被设置成为自己
                    if (id.equals(children.getParentId())) {
//                        if (parent.getChildren() == null) {
//                            parent.setChildren(new ArrayList<T>());
//                        }
                        parent.initChildren();
                        parent.getChildren().add(children);
                    }
                } else if (id.equals(parent.getParentId())) {

                    selfIdEqSelfParent.add(id);
                }
            }
        }
        // 找出根节点集合
        List<T> trees = new ArrayList<>();

//        List<Serializable> allIds = new ArrayList<>();
//        for (T baseNode : treeNodes) {
//            allIds.add(baseNode.getId());
//        }

        List<? extends Serializable> allIds = treeNodes.stream().map(node -> node.getId()).collect(Collectors.toList());
        for (T baseNode : treeNodes) {
            if (!allIds.contains(baseNode.getParentId()) || selfIdEqSelfParent.contains(baseNode.getParentId())) {
                trees.add(baseNode);
            }
        }
        return trees;
    }
}
