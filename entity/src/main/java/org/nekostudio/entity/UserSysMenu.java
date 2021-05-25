package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author neko
 */
@TableName("user_menu_mid")
@Data
public class UserSysMenu {

    private Integer id;

    private Integer userId;

    private Integer menuId;
}
