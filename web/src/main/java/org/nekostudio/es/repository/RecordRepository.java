package org.nekostudio.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author neko
 */
public interface RecordRepository extends ElasticsearchRepository<Record,Integer>{
}
