package com.ancestry.Ultron.repository;


import java.util.List;

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


public interface StudentRepository {

	public Student saveStudent(Student student, MultipartFile file);

	public List<Student> getAllStudents();

	public void deleteStudent(Long id);

	public List<Student> duplicateStudent();

	public Student updateStudent(Student student, String filename);

	public Student getStudent(Long id, String filename);

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
