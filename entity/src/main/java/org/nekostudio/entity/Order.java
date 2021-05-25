package org.nekostudio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author neko
 */
@Data
@AllArgsConstructor
public class Order {
    private Integer id;
    private List<Good> goods;
    private Integer payState;
    private Integer orderState;
}
