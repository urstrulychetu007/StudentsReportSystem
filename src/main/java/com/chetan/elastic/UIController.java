package com.chetan.elastic;

import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UIController {
  


    @RequestMapping(value = "/sem/1",method = RequestMethod.GET)
    public String homepage(ModelMap modelMap) throws UnirestException {
//        Iterable<Students> students = ElasticApplication.getStudents();


HttpResponse<JsonNode> response1 = Unirest.get("http://127.0.0.1:8080/getSemesterWiseData/1").asJson();
 JSONArray array = (response1.getBody().getArray());
 ArrayList<JSONObject> arrayList = new ArrayList<>();
 int data_count = array.length();
 float classAverage = 0;
 for(int item = 0;item<array.length();item++)
 { 
    arrayList.add(array.getJSONObject(item));
    classAverage+=array.getJSONObject(item).getInt("total");
 }
 classAverage=classAverage/data_count;
 float classAvgPercentage = classAverage/3;
 System.out.println(arrayList+" "+classAverage+" "+data_count+" "+classAverage/data_count);



 HttpResponse<JsonNode> response3 = Unirest.get("http://127.0.0.1:8080/getLeaderBoard").asJson();
 JSONArray array3 = (response3.getBody().getArray());
 ArrayList<JSONObject> arrayList3 = new ArrayList<>();
 int data_count3 = array3.length();

 for(int leaderboard_item = 0;leaderboard_item<array3.length();leaderboard_item++)
 { 
    arrayList3.add(array3.getJSONObject(leaderboard_item));
 }




        HttpResponse <JsonNode> response2 = Unirest.get("http://localhost:8080/getStudents").asJson();
        JSONObject jsonObject1 =  response2.getBody().getObject();
        JSONArray array1 = jsonObject1.getJSONArray("content");
        ArrayList<JSONObject> arrayList1 = new ArrayList<>();
        int data_count1 = array1.length();

        for(int i=0;i<data_count1;i++)
        {
            arrayList1.add(array1.getJSONObject(i));
        }
        Students stud = new Students();
        // modelMap.addAttribute("Students", stud);
        modelMap.addAttribute("Students", stud);
        modelMap.addAttribute("array",arrayList);
        modelMap.addAttribute("array3",arrayList3);
        modelMap.addAttribute("data_count",data_count);
        modelMap.addAttribute("data_count3",data_count3);
        modelMap.addAttribute("heading","Semester 1");
        modelMap.addAttribute("percentage", classAvgPercentage);
        modelMap.addAttribute("average", classAverage);
        modelMap.addAttribute("array1",arrayList1);
        modelMap.addAttribute("data_count1",data_count1);

        return "home";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(ModelMap modelMap) throws UnirestException {
//        Iterable<Students> students = ElasticApplication.getStudents();


HttpResponse<JsonNode> response1 = Unirest.get("http://127.0.0.1:8080/getSemesterWiseData/2").asJson();
 JSONArray array = (response1.getBody().getArray());
 ArrayList<JSONObject> arrayList = new ArrayList<>();
 int data_count = array.length();
 float classAverage = 0;
 for(int item = 0;item<array.length();item++)
 { 
    arrayList.add(array.getJSONObject(item));
    classAverage+=array.getJSONObject(item).getInt("total");
 }
 classAverage=classAverage/data_count;
 float classAvgPercentage = classAverage/3;


 HttpResponse<JsonNode> response3 = Unirest.get("http://127.0.0.1:8080/getLeaderBoard").asJson();
 JSONArray array3 = (response3.getBody().getArray());
 ArrayList<JSONObject> arrayList3 = new ArrayList<>();
 int data_count3 = array3.length();

 for(int leaderboard_item = 0;leaderboard_item<array3.length();leaderboard_item++)
 { 
    arrayList3.add(array3.getJSONObject(leaderboard_item));
 }


       
        HttpResponse <JsonNode> response2 = Unirest.get("http://localhost:8080/getStudents").asJson();
        JSONObject jsonObject1 =  response2.getBody().getObject();
        JSONArray array1 = jsonObject1.getJSONArray("content");
        ArrayList<JSONObject> arrayList1 = new ArrayList<>();
        int data_count1 = array1.length();

        for(int i=0;i<data_count1;i++)
        {
            arrayList1.add(array1.getJSONObject(i));
        }
        Students stud = new Students();
        
        // modelMap.addAttribute("Students", stud);
        modelMap.addAttribute("Students", stud);
        modelMap.addAttribute("array",arrayList);
        modelMap.addAttribute("array3",arrayList3);
        modelMap.addAttribute("data_count",data_count);
        modelMap.addAttribute("data_count3",data_count3);
        modelMap.addAttribute("average", classAverage);
        modelMap.addAttribute("percentage", classAvgPercentage);
        modelMap.addAttribute("heading","Semester 2");
        modelMap.addAttribute("array1",arrayList1);
        modelMap.addAttribute("data_count1",data_count1);

        return "home";
    }

    @RequestMapping(value = "/addstudent",method = RequestMethod.POST)
    public String addstudent(@ModelAttribute("Students")Students student) throws  UnirestException
    {
      
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", student.getId());
        jsonObject.put("student_name", student.getStudent_name());
        Students student1 = new Students(student.getId(), student.getStudent_name());
        // student1.setId(student.getId());
        System.out.print(student);
        
        // return student.toString();
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://127.0.0.1:8080/addStudent").header("content-type", "application/json").body(jsonObject).asString();
        System.out.println(response.getStatus());
        System.out.println(jsonObject);
        return  "redirect:/";
    }

    @RequestMapping(value = "/viewdetails/{id}/addstudentdetails",method = RequestMethod.POST)
    public String addstudentdetails(@ModelAttribute("StudentsData")StudentsData student) throws  UnirestException
    {
      
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", student.getId());
        jsonObject.put("marks_in_english", student.getMarks_in_english());
        jsonObject.put("marks_in_maths", student.getMarks_in_maths());
        jsonObject.put("marks_in_science", student.getMarks_in_science());
        jsonObject.put("semester", student.getSemester());


        // Students student1 = new Students(student.getId(), student.getStudent_name());
        // student1.setId(student.getId());
        System.out.print(student);
        
        // return student.toString();
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://127.0.0.1:8080/addStudentDetails/"+(student.getUsn())).header("content-type", "application/json").body(jsonObject).asString();
        System.out.println(response.getStatus());
        System.out.println(jsonObject);
        return  "redirect:/";
    }


    @RequestMapping(value="/viewdetails/{id}/{name}",method=RequestMethod.GET)
    public String viewPage(ModelMap modelMap,@PathVariable String id,@PathVariable String name) throws UnirestException
    {
        String isHide = "";
        System.out.println(id);

        HttpResponse<JsonNode> response1 = Unirest.get("http://127.0.0.1:8080/getStudentDetail/"+id).asJson();
        JSONArray array = (response1.getBody().getArray());
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        int data_count = array.length();
        float classAverage = 0;
        for(int item = 0;item<array.length();item++)
        { 
            arrayList.add(array.getJSONObject(item));
            classAverage+=array.getJSONObject(item).getInt("total");
        }
        classAverage=classAverage/data_count;
        StudentsData stud = new StudentsData();
        if(data_count>=2)
         isHide="hidden";

         String randId  = String.valueOf((int)(Math.random()*(100)+1));  
        int sems = 0;
        if( data_count==1)
            sems = 2;
        else if(data_count==0)
          sems=1;    
        // modelMap.addAttribute("Students", stud);
        modelMap.addAttribute("StudentsData", stud);
        modelMap.addAttribute("average", classAverage);
        modelMap.addAttribute("heading",isHide);
        modelMap.addAttribute("array",arrayList);
        modelMap.addAttribute("id",randId);
        modelMap.addAttribute("name",name);
        modelMap.addAttribute("semester",sems);
        modelMap.addAttribute("usn",id);
        modelMap.addAttribute("data_count",data_count);

        return "view";

    }

}
