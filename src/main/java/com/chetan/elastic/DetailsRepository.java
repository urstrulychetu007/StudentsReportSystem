package com.chetan.elastic;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DetailsRepository extends ElasticsearchRepository<StudentsData,String>{

    List<StudentsData> findByUsn(String student_id);

    List<StudentsData> findBySemester(Integer semester, Sort sort);
    
}
