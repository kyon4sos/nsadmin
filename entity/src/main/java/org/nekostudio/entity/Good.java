package org.nekostudio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author neko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private Integer id;
    private String title;
    private String desc;
    private List<String> mainImage;
    private BigDecimal price;
    private Integer storeCount;
}
