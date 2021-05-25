package org.nekostudio.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Data
public class TreeBuilder<T extends ITree<T>>{

    private List<T> parent=new ArrayList<>();


    public List<T> getChildren(T current, List<T> list) {
        return list.stream()
                .filter(e -> e.treeParentId()!=null && e.treeParentId().equals(current.treeId()))
                .peek(e -> e.addTreeChildren( getChildren(e, list)))
                .collect(Collectors.toList());
    }

    public List<T> build(List<T> list) {
       return list.stream()
               .filter(t->t.treeParentId()==null || t.treeParentId()==0)
               .peek(e-> e.addTreeChildren((getChildren(e, list))))
               .collect(Collectors.toList());
    }
}
