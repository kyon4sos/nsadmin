package org.nekostudio.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author neko
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {
    /**
     * 通用成功返回消息
     */
    SUCCESS(20000,"成功"),
    /**
     * 通用错误返回消息
     */
    ERROR(40000,"失败"),
    NO_LOGIN(40001,"请登录"),
    WRONG_PASSWORD(40002,"用户名或密码错误"),
    NO_PERMISSION(40003,"权限不足"),
    NO_SUPPORT(40004,"不支持的方法"),
    ARGS_EXCEPTION(40005,"参数异常"),
    BAD_REQUEST_EXCEPTION(40006,"错误的请求"),
    TOO_MANY_REQUEST_EXCEPTION(40007,"请求太频繁，请稍后再试"),
    USERNAME_EXIST(40100,"用户名已存在"),
    PHONE_EXIST(40101,"手机号码已存在"),
    SERVER_EXCEPTION(50000,"系统异常");
    private Integer code;
    private String msg;
}
