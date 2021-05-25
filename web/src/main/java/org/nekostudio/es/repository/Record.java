package org.nekostudio.es.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.nekostudio.enums.Operation;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
    private Object data;
    private String username;

    @Field(type = FieldType.Date,format = DateFormat.epoch_millis)
    private LocalDateTime time;

}
