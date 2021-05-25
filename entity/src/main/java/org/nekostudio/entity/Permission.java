package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author neko
 */
@Data
public class Permission {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    private String displayName;
}
