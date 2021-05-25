package org.nekostudio.dto;

import java.util.List;

/**
 * @author neko
 */
public interface ITree<T> {
    Integer treeParentId();
    Integer treeSort();
    Integer treeId();
    void addTreeChildren(List<T> list);
}
