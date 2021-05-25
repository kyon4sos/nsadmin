package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role  {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Length(min = 3,max = 16, message = "角色名称3-6个字符")
    private String name;

    @Length(min = 3,max = 16, message = "角色名称3-6个字符")
    private String displayName;

    private Boolean state;

    private String description;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<SysMenu> sysMenus=new ArrayList<>();

    @TableField(exist = false)
    private List<Integer> menuIds=new ArrayList<>();
}
