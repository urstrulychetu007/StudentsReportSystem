package com.chetan.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;



public interface Repository extends ElasticsearchRepository<Students,String> {

}
