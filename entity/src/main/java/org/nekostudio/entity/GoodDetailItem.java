package org.nekostudio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author neko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDetailItem {
    private Integer id;
    private String url;
    private Integer order;
    private String docType;
}
