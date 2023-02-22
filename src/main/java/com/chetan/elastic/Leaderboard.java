package com.chetan.elastic;

public class Leaderboard {
    private String student_name;
    private int total;
    private float average;
    private String usn;

    public Leaderboard(String student_name,String usn, int total, float average) {
        this.student_name = student_name;
        this.total = total;
        this.usn = usn;
        this.average = average;
    }

    
    public String getStudent_name() {
        return student_name;
    }

    public int getTotal() {
        return total;
    }

    public float getAverage() {
        return average;
    }


    public String getUsn() {
        return usn;
    }
    
}
