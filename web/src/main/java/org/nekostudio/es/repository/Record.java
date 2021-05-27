package org.nekostudio.es.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.nekostudio.enums.Operation;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

/**
 * @author neko
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="record")
public class Record {

    @Id
    private String id;
    private Integer relationId;
    private Operation operation;
    private String description;
    private Object argData;
    private String username;
    private LocalDateTime updateTime;

}
