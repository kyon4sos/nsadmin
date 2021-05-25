package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.nekostudio.dto.ITree;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends BaseEntity implements GrantedAuthority, ITree<SysMenu> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    @NotBlank(message = "url不能为空")
    private String url;

    private String remark;

    @NotBlank(message = "method不能为空")
    private String httpMethod;

    private String permission;

    private Integer permissionCode;

    private Boolean disable;

    private Boolean top;

    private Integer sort;

    private Integer parentId;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

    @JsonIgnore
    @Override
    public String getAuthority() {
        return permission;
    }

    @Override
    public Integer treeParentId() {
        return parentId;
    }

    @Override
    public Integer treeSort() {
        return sort;
    }

    @Override
    public Integer treeId() {
        return id;
    }

    @Override
    public void addTreeChildren(List<SysMenu> list) {
        this.children.addAll(list);
    }


}
