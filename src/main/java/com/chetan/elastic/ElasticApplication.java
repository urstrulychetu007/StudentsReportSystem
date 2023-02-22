package com.chetan.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.chetan.elastic"})
public class ElasticApplication {

	@Autowired
	private  Repository repository;
	
	@Autowired
	private DetailsRepository detailsRepository;


	@PostMapping("/addStudent")
	public int addStudent(@RequestBody Students student)
	{
		repository.save(student);
		return 1;
	}


	@PostMapping("/addStudentDetails/{student_id}")
	public int addDetails(@RequestBody StudentsData details,@PathVariable String student_id)
	{
		details.setUsn(student_id);
		details.setTotal(details.getMarks_in_english()+details.getMarks_in_maths()+ details.getMarks_in_science());
		detailsRepository.save(details);		
		return 1;
	}

	@GetMapping("/getStudents")
	public  Iterable<Students> getStudents()
	{
		return repository.findAll();
	}

	@GetMapping("/getStudentDetail/{student_id}")
	public List<StudentsData> getDataById(@PathVariable String student_id)
	{
		return detailsRepository.findByUsn(student_id);
	}


	@GetMapping("/getStudentName/{id}")
	public String getStudentName(@PathVariable String id)
	{
		String name = repository.findById(id).get().getStudent_name();
		return name;
	}

	@GetMapping("/getSemesterWiseData/{semester}")
	public List<StudentInfo> getDataBySemester(@PathVariable Integer semester)
	{
		
		List<StudentsData> studentDetails = detailsRepository.findBySemester(semester,Sort.by("total").descending());
		List<StudentInfo> studentInfos = new ArrayList<>();
		List<StudentInfo> studentInfo = new ArrayList<>();
		for(int i=0;i<studentDetails.size();i++)
		{
			String student_name = getStudentName((studentDetails.get(i).getUsn()));
			System.out.println(student_name);
			// studentInfos.add(new StudentInfo(student_name, studentDetails.get(i)));
			studentInfo.add(new StudentInfo(studentDetails.get(i).getUsn(),student_name,studentDetails.get(i).getSemester(),studentDetails.get(i).getMarks_in_english(),studentDetails.get(i).getMarks_in_maths(),studentDetails.get(i).getMarks_in_science(),studentDetails.get(i).getTotal()));
		}
		// System.out.println(studentInfos.get(0).getStudent_name()+" "+studentInfos.get(0).getTotal());
		return studentInfo;

	} 


	@GetMapping("/getLeaderBoard")
	public List<Leaderboard> getLeaderboard()
	{
		Iterable<Students> students = getStudents();
		List<Leaderboard> leaderboards = new ArrayList<>();
		for (Students students2 : students) {
			List<StudentsData> data = getDataById(students2.getId());
			int total = 0;
			float average = 0;
			for(int i=0;i<data.size();i++)
			{
				total+=data.get(i).getTotal();
			}
			average = total/2;
			leaderboards.add(new Leaderboard(students2.getStudent_name(),students2.getId(), total, average));
		}
		Collections.sort(leaderboards,new LeaderboardCompartor());
		return leaderboards;
	}

	class LeaderboardCompartor implements Comparator<Leaderboard> {
  
		// override the compare() method
		public int compare(Leaderboard s1, Leaderboard s2)
		{
			if (s1.getAverage() == s2.getAverage())
				return 0;
			else if (s1.getAverage() < s2.getAverage())
				return 1;
			else
				return -1;
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(ElasticApplication.class, args);
	}

}
