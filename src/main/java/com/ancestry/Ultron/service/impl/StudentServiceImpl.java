package com.ancestry.Ultron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ancestry.Ultron.model.City;
import com.ancestry.Ultron.model.Country;
import com.ancestry.Ultron.model.CourseName;
import com.ancestry.Ultron.model.DriveAttending;
import com.ancestry.Ultron.model.Gender;
import com.ancestry.Ultron.model.PrefferedLocation;
import com.ancestry.Ultron.model.State;
import com.ancestry.Ultron.model.WillingnessRelocate;
import com.ancestry.Ultron.model.Year;
import com.ancestry.Ultron.model.Student;
import com.ancestry.Ultron.repository.StudentRepository;
import com.ancestry.Ultron.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student,MultipartFile file) {
		return studentRepository.saveStudent(student,file);
	}

	@Override
	public Student getStudent( Long id, String filename) {
		return studentRepository.getStudent(id,filename);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}


	
	@Override
	public Student updateStudent(Student student,String filename) {
			return studentRepository.updateStudent(student,filename);
	}

	@Override
	public void deleteStudent(Long studentId) {
		studentRepository.deleteStudent(studentId);
	}

	

	@Override
	public List<Student> duplicateStudent() {
		return studentRepository.duplicateStudent();
	}
	
	
			public List<Country>getCountries(){
			return studentRepository.getCountries();
		}
		
		public List<Gender>getGenders(){
			return studentRepository.getGenders();
		}
		public List<Year> getYears()
		{
			return studentRepository.getYears();
		}
		
		public List<State>getStates(){
			return studentRepository.getStates();
		}

		public List<City> getCities() {
			return studentRepository.getCities();
		}

		public List<PrefferedLocation> getPrefferedLocations() {
			return studentRepository.getPrefferedLocations();
		}

		public List<CourseName> getCourseNames() {
			return studentRepository.getCourseNames();
		}

		public List<WillingnessRelocate> getWillingnessRelocates() {
			return studentRepository.getWillingnessRelocates() ;
		}

		public List<DriveAttending> getDriveAttending() {
			return studentRepository.getDriveAttending();
		}


	}

	


