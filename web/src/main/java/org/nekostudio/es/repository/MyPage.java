package org.nekostudio.es.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author neko
 */
@Getter
@Setter
@JsonIgnoreProperties(value = {"totalElements","totalElements","content"})
public class MyPage<T>  extends PageImpl<T> {

    private Integer current;
    private Long total;
    private int size;
    private List<T> records;

    public MyPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.records.addAll(content);
        this.current = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.total = total;
    }
    public MyPage(Page<T> page) {
        super(page.getContent(),page.getPageable(),page.getTotalElements());
        this.records = super.getContent();
        this.current = super.getPageable().getPageNumber();
        this.size = super.getSize();
        this.total =super.getTotalElements();
    }
}
