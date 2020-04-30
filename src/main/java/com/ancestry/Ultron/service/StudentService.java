package com.ancestry.Ultron.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ancestry.Ultron.model.City;
import com.ancestry.Ultron.model.Country;
import com.ancestry.Ultron.model.CourseName;
import com.ancestry.Ultron.model.DriveAttending;
import com.ancestry.Ultron.model.Gender;
import com.ancestry.Ultron.model.PrefferedLocation;
import com.ancestry.Ultron.model.State;
import com.ancestry.Ultron.model.Student;
import com.ancestry.Ultron.model.WillingnessRelocate;
import com.ancestry.Ultron.model.Year;


@Service
public interface StudentService {
	
	public Student saveStudent(Student student,  MultipartFile file);

	public  Student getStudent(Long studentId, String fileName);
	
		
	public List<Student> getAllStudents();

	public void deleteStudent(Long studentId); 
	
	public List<Student> duplicateStudent();

	public Student updateStudent(Student student, String filename);

	public List<Country> getCountries();

	public List<State> getStates();

	public List<City> getCities();

	public List<PrefferedLocation> getPrefferedLocations();

	public List<CourseName> getCourseNames();

	public List<WillingnessRelocate> getWillingnessRelocates();

	public List<DriveAttending> getDriveAttending();

	public List<Gender> getGenders();

	public List<Year> getYears();



	

	
}
