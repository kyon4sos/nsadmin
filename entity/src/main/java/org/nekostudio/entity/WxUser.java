package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * wx_user
 *
 * @author
 */
@Data
@Accessors(chain = true)
public class WxUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String nickname;

    private String openid;

    private String sessionKey;

    private String unionid;

    /**
     * 真实姓名
     */
    private String realname;

    private String username;

    private String avatar;

    private String phone;
    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    private static final long serialVersionUID = 1L;
}