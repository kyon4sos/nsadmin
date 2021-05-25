package org.nekostudio.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.nekostudio.annotation.SensitiveInfo;
import org.nekostudio.enums.SensitiveType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Data
@Accessors(chain = true)
@TableName(resultMap = "BaseResultMap")
public class AppUser implements UserDetails {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String realname;

    @SensitiveInfo(type = SensitiveType.PHONE)
    private String nickname;

    private String username;

    private String password;

    private String phone;

    private String avatar;

    private String gender;

    private LocalDateTime lastLoginTime;

    @TableField(fill = FieldFill.INSERT)

    private LocalDateTime registerTime;
    @TableField(fill = FieldFill.INSERT)

    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)

    private LocalDateTime updateTime;

    private Integer loginCount;

    private String salt;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    @TableField(exist = false)
    private List<SysMenu> menus = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        roles.forEach(r -> {
                    if (r.getSysMenus() != null) {
                        menus.addAll(r.getSysMenus());
                    }
                }
        );
        return menus.stream().map(menu -> new SimpleGrantedAuthority(menu.getAuthority()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
