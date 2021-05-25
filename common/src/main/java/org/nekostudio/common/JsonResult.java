package org.nekostudio.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author neko
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    private JsonResult(ResultEnum res) {
        this.code = res.getCode();
        this.msg = res.getMsg();
    }

    public static JsonResult ok() {
        return new JsonResult(ResultEnum.SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(ResultEnum.SUCCESS).setData(data);
    }

    public static JsonResult ok(String msg) {
        return new JsonResult(ResultEnum.SUCCESS).setMsg(msg).setData(null);
    }

    public static JsonResult fail(String defaultMessage, Integer code) {
        return new JsonResult().setMsg(defaultMessage).setCode(code).setData(null);
    }
    public static JsonResult fail() {
        return new JsonResult(ResultEnum.ERROR);
    }
    public static JsonResult fail(ResultEnum result) {
        return new JsonResult(result);
    }
    public static JsonResult fail(String msg) {
        return new JsonResult().setMsg(msg).setCode(50001);
    }
}
