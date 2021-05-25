package org.nekostudio.dto;

import lombok.Data;

/**
 * @author neko
 */
@Data
public class SysMenuDto {

    private Integer id;

    private String name;

    private Boolean top;

    private Integer sort;

    private Integer parentId;

}

