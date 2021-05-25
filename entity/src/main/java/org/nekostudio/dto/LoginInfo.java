package org.nekostudio.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginInfo {
    @NotBlank(message = "用户名密码不能为空")
    private String  username;
    @NotBlank(message = "用户名密码不能为空")
    private String password;

}
