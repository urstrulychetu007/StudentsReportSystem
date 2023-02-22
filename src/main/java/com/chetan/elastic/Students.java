package com.chetan.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {

    @Id
    private String id;
    private String student_name;
}
    
