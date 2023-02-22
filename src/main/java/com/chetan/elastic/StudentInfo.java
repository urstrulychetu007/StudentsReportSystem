package com.chetan.elastic;

public class StudentInfo {

    private String usn;
    private String student_name;
    private int semester;
    private int marks_in_english;
    private int marks_in_maths;
    private int marks_in_science;
    private int total;
    private StudentsData studentsData;
    
    public StudentInfo(String student_name, StudentsData studentsData) {
        this.student_name = student_name;
        this.studentsData = studentsData;
        this.usn = studentsData.getUsn();
        this.marks_in_english = studentsData.getMarks_in_english();
        this.marks_in_maths = studentsData.getMarks_in_maths();
        this.marks_in_science = studentsData.getMarks_in_science();
        this.total = studentsData.getTotal();
        this.semester = studentsData.getSemester();
        
    }

   
    
    

  

    public StudentInfo(String usn, String student_name, int semester, int marks_in_english, int marks_in_maths,
            int marks_in_science, int total) {
        this.usn = usn;
        this.student_name = student_name;
        this.semester = semester;
        this.marks_in_english = marks_in_english;
        this.marks_in_maths = marks_in_maths;
        this.marks_in_science = marks_in_science;
        this.total = total;
    }







    public String getUsn() {
        return usn;
    }
    public String getStudent_name() {
        return student_name;
    }
    public int getSemester() {
        return semester;
    }
    public int getMarks_in_english() {
        return marks_in_english;
    }
    public int getMarks_in_maths() {
        return marks_in_maths;
    }
    public int getMarks_in_science() {
        return marks_in_science;
    }
    public int getTotal() {
        return total;
    }
   
    

}
