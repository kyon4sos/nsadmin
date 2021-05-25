package org.nekostudio.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author neko
 */
@Data
public class TreeNode<T> {

    private Integer id;
    private Integer pid;
    private T obj;
    private Set<T> children;
    private Integer primaryKey;

    public void primaryKey(Integer obj) {
        primaryKey = obj;
    }
    public Set<T> builder(List<T> list,Integer id) {
        TreeNode<T> treeNode = new TreeNode<>();

        return  null;
    }
}
