package org.nekostudio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author neko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetail {
    private Integer id;
    private UUID uuid;
    private List<GoodDetailItem> detail;
}
