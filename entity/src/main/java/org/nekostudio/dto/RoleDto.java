package org.nekostudio.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@Data
public class RoleDto {

    private Integer id;

    @Length(min = 3,max = 16, message = "角色名称3-6个字符")
    private String name;
    @Length(min = 3,max = 16, message = "角色中文名3-6个字符")
    private String displayName;

    private Boolean state;

    private String description;

    private List<Integer> menuIds = new ArrayList<>();
}
