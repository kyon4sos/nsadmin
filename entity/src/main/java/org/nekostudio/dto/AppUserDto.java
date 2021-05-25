package org.nekostudio.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@Data
public class AppUserDto {

    private Integer id;

    @Length(min = 6,max = 20,message = "用户名字符长度为6-20")
    private String username;

    private String nickname;

    @Length(min = 6,max = 20,message = "密码长度为6-20")
    private String password;

    @Length(min = 2,max = 8,message = "用户姓名长度为2-8")
    private String realname;

    @Length(min = 11,max = 11,message = "手机号长度为11")
    private String phone;

    private List<Integer> roleIds = new ArrayList<>();
    private String avatar;

}
