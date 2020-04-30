
package com.ancestry.Ultron.controller;


import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import com.ancestry.Ultron.model.City;
import com.ancestry.Ultron.model.Country;
import com.ancestry.Ultron.model.CourseName;
import com.ancestry.Ultron.model.DriveAttending;
import com.ancestry.Ultron.model.Gender;
import com.ancestry.Ultron.model.PrefferedLocation;
import com.ancestry.Ultron.model.State;
import com.ancestry.Ultron.model.WillingnessRelocate;
import com.ancestry.Ultron.model.Year;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ancestry.Ultron.model.Student;
import com.ancestry.Ultron.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	
	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		Student student = new Student();
		map.put("student", student);
		map.put("studentList", studentService.getAllStudents());
		return "student";
	}
	
	
	@PostMapping("")
	public ResponseEntity<Object> saveStudent(@Valid @RequestPart("file")MultipartFile file,@RequestPart("student") @RequestBody Student data) throws URISyntaxException {
		Student student = studentService.saveStudent(data,file);
		return ResponseEntity.ok(student);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student data,String filename) throws URISyntaxException {
			Student student = studentService.updateStudent(data, filename);
		return ResponseEntity.ok(student);
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getStudent(@PathVariable("id") Long id, String filename) {
		
			//Student student =studentService.getStudent(id,filename);
//			byte[] bytes = StreamUtils.copyToByteArray(studentService.getStudent(id,fileName));
//			byte[] bytes = org.springframework.util.StreamUtils.copyToByteArray(studentService.getStudent(id,fileName))
//					ByteArrayResource resource = new ByteArrayResource(bytes);
//					HttpHeaders header = new HttpHeaders();
//					header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
		return ResponseEntity.ok(studentService.getStudent(id, filename));	
//					headers(header)
//					.contentLength(bytes.length)
//					.contentType(MediaType.parseMediaType("application/octet-stream"))
//					.body(resource);
			
			
	}
	
	


	@GetMapping("")
	public ResponseEntity<Object> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<Object>("Deleted Successfully!!", HttpStatus.OK);
	}
	@GetMapping("/duplicate")
	public ResponseEntity<Object> duplicateStudent() {
		return ResponseEntity.ok(studentService.duplicateStudent());
	}
		@GetMapping("/countries")
		public List<Country>getCountries(){
			return studentService.getCountries();
		}

		@GetMapping("/states")
		public List<State>getStates(){
			return studentService.getStates();
		}   
		
		@GetMapping("/cities")
		public List<City>getCities(){
			return studentService.getCities();
		}

		@GetMapping("/genders")
		public List<Gender>getGenders(){
			return studentService.getGenders();
		}
		
		@GetMapping("/years")
		public List<Year>getYears(){
			return studentService.getYears();
		}
		
		
		@GetMapping("/prefferedlocations")
		public List<PrefferedLocation>getPrefferedlocations(){
			return studentService.getPrefferedLocations();
		}
		
		@GetMapping("/coursenames")
		public List<CourseName>getCourseNames(){
			return studentService.getCourseNames();
		}
		
		@GetMapping("/willingnessrelocates")
		public List<WillingnessRelocate>getWillingnessRelocates(){
			return studentService.getWillingnessRelocates();
		}
		
		@GetMapping("/driveattendings")
		public List<DriveAttending> getDriveAttending(){
			return studentService.getDriveAttending();
		}
		
	}

	
	
	

