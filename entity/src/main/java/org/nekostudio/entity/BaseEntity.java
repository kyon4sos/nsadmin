package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author neko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime deleteTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateBy;

}
