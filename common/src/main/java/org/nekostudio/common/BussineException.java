package org.nekostudio.common;

import lombok.Data;

/**
 * @author neko
 */
@Data
public class BussineException extends RuntimeException{
    private Integer code;
    private String msg;

    public BussineException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg=msg;
    }
    public BussineException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }
}
