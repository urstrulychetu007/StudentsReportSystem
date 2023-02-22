// private String student_semester;
//     private int marks_in_english;
//     private int marks_in_maths;

//     private int marks_in_science;
//     private int total
package com.chetan.elastic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsData {
    @Id
    private String id;
    private String usn;
    private int marks_in_english;
    private int marks_in_science;
    private int marks_in_maths;
    private int total;
    private int semester;

}
