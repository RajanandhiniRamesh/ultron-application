package com.ancestry.Ultron.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ancestry.Ultron.model.City;
import com.ancestry.Ultron.model.Country;
import com.ancestry.Ultron.model.CourseName;
import com.ancestry.Ultron.model.DriveAttending;
import com.ancestry.Ultron.model.Gender;
import com.ancestry.Ultron.model.PrefferedLocation;
import com.ancestry.Ultron.model.State;
import com.ancestry.Ultron.model.WillingnessRelocate;
import com.ancestry.Ultron.model.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ancestry.Ultron.model.Student;
import com.ancestry.Ultron.repository.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired
	DataSource datasource;


	
	@Override
	public Student saveStudent(Student student,MultipartFile file) {
		try (Connection connection = datasource.getConnection()) {
			String studentSql = "INSERT INTO student_details (email,employeeName,employeeId,candidateName,candidateEmail,dob,contactNo,gender,nationality,currentLocation,country,state,city,pincode,prefferedLocation,collegeName,courseName,year,scoreNineteen,scoreTwenty,twelveMark,tenMark,willingnessRelocate,driveAttending,fileName,resume) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(studentSql,Statement.RETURN_GENERATED_KEYS)) {
				prepareStatement(statement, student);
				statement.setString(33, file.getOriginalFilename());	
				
				try {
					statement.setBlob(34, file.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int affectedRows = statement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							student.setId(generatedKeys.getLong(1));
						} else {
							throw new SQLException("Creating student failed, no ID obtained.");
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student getStudent(Long id, String filename) {
		
				Student student = new Student();
		try (Connection connection = datasource.getConnection()) {
			String getSql = "SELECT id,email,employeeName,employeeId,candidateName,candidateEmail,dob,contactNo,gender,nationality,currentLocation,country,state,city,pincode,prefferedLocation,collegeName,courseName,year,scoreNineteen,scoreTwenty,twelveMark,tenMark,willingnessRelocate,driveAttending,fileName,resume  FROM student_details WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(getSql)) {
				statement.setLong(1, id);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					if (result.next()) {
						  Blob blob = result.getBlob("document");
						  InputStream inputStream = blob.getBinaryStream();
					  transformStudent(result, student);
					  
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return student;
	}
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try (Connection connection = datasource.getConnection()) {
			String getSql = "SELECT s.id,s.email,s.employeeName,s.employeeId,s.candidateName,s.candidateEmail,s.dob,s.contactNo,s.gender,g.name as genderName,s.nationality,s.currentLocation,s.country,co.name as countryName,s.state,st.name as stateName,s.city,c.name as cityName,s.pincode,s.prefferedLocation,p.name as locationName,s.collegeName,s.courseName,cn.name as branchName,s.year,s.scoreNineteen,s.scoreTwenty,s.twelveMark,s.tenMark,s.willingnessRelocate,w.name as willingnessRelocateName,s.driveAttending,d.name as driveAttendingName ,s.fileName,s.resume FROM student_details s \r\n" + 
					"INNER JOIN gender g ON s.gender = g.id\r\n" + 
					"INNER JOIN country co ON s.country=co.id \r\n" + 
					"INNER JOIN state st ON s.state=st.id\r\n" + 
					"INNER JOIN city c ON s.city = c.id\r\n" + 
					"INNER JOIN prefferedlocation p on s.prefferedLocation=p.id\r\n" + 
					"INNER JOIN coursename cn ON s.courseName=cn.id\r\n" + 
					"INNER JOIN willingnessRelocate w ON s.willingnessRelocate=w.id\r\n" + 
					"INNER JOIN driveAttending d ON s.driveAttending=d.id";
			try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					
				ResultSet result = statement.executeQuery();
				
				while (result.next()) {
					Student student = new Student();
					transformStudent(result, student);
					students.add(student);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return students;
	}

	@Override
	public Student updateStudent(Student student,String filename) {
		try (Connection connection = datasource.getConnection()) {
			String updateSql = "UPDATE student_details SET email = ?,employeeName = ?,employeeId = ?,candidateName = ?,candidateEmail = ?,dob = ?,contactNo = ? ,gender = ?,nationality = ?,currentLocation = ?,country=?,state = ?,city = ?,pincode = ?, prefferedLocation = ?,collegeName = ?,courseName = ?,year = ?,scoreNineteen = ?, scoreTwenty = ?,twelveMark = ?,tenMark = ?,willingnessRelocate = ?,driveAttending = ?, fileName = ?, resume=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(updateSql);
				try {
	            	 File file = new File(filename);
					FileInputStream input = new FileInputStream(file);
					 statement.setBinaryStream(1, input);
			    	prepareStatement(statement, student);
					statement.setLong(35, student.getId());
			statement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return student;

	}

	@Override
	public void deleteStudent(Long studentId) {
		Student student = new Student();
		try (Connection connection = datasource.getConnection()) {
			String deleteSql = "DELETE FROM student_details WHERE id =?";
			PreparedStatement statement = connection.prepareStatement(deleteSql);
			statement.setLong(1, studentId);
			statement.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	

	public List<Student> duplicateStudent() {
		List<Student> students = new ArrayList<Student>();
		try (Connection connection = datasource.getConnection()) {
			String duplicateSql = "select t1.* from student_details t1 inner join \r\n"
					+ "(select * from student_details group by candidateEmail,contactNo,candidateName having count(*)>1) t2 on t2.candidateEmail=t1.candidateEmail and t1.contactNo=t2.contactNo and t1.candidateName=t2.candidateName";
				try (PreparedStatement statement = connection.prepareStatement(duplicateSql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					Student student = new Student();
					transformStudent(result, student);
					students.add(student);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return students;
	}
	
	
	

		public List<Country> getCountries() {
			List<Country> countries = new ArrayList<Country>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT id,name,sortName FROM country where name='India'";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						Country country = new Country();
						country.setId(result.getLong("id"));
						country.setSortName(result.getString("sortName"));
						country.setName(result.getString("name"));
						countries.add(country);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return countries;
		}

			
		public List<State> getStates() {
			List<State> states = new ArrayList<State>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT id,name,country_id FROM state  where country_id=101";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						State state = new State();
						state.setId(result.getLong("id"));
						state.setName(result.getString("name"));
						state.setCountryId(result.getLong("country_id"));
						states.add(state);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return states;
		}

		public List<City> getCities() {
			List<City> cities = new ArrayList<City>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "select id,name,state_id from city where state_id BETWEEN 1 AND 41";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						City city = new City();
						city.setId(result.getLong("id"));
						city.setName(result.getString("name"));
						city.setStateId(result.getLong("state_id"));
						cities.add(city);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return cities;
		}
		public List<Gender> getGenders() {
			List<Gender> genders = new ArrayList<Gender>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT id,name FROM gender";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						Gender gender = new Gender();
						gender.setId(result.getLong("id"));
						gender.setName(result.getString("name"));
						genders.add(gender);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return genders;
		}
		public List<Year> getYears() {
			List<Year> years = new ArrayList<Year>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT id,name FROM year";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						Year year = new Year();
						year.setId(result.getLong("id"));
						year.setName(result.getLong("name"));
						years.add(year);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return years;
		}

		public List<PrefferedLocation> getPrefferedLocations() {
			List<PrefferedLocation> prefferedLocations= new ArrayList<PrefferedLocation>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT id,name FROM prefferedlocation";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						PrefferedLocation prefferedLocation = new PrefferedLocation();
						prefferedLocation.setId(result.getLong("id"));
						prefferedLocation.setName(result.getString("name"));
						prefferedLocations.add(prefferedLocation);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return prefferedLocations;
		}


		public List<CourseName> getCourseNames() {
			List<CourseName> courseNames= new ArrayList<CourseName>();
		try (Connection connection = datasource.getConnection()) {
			String getSql = "SELECT name,sortName FROM coursename";
			try (PreparedStatement statement = connection.prepareStatement(getSql)) {
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					CourseName courseName = new CourseName();
				
					courseName.setName(result.getString("name"));
					courseName.setSortName(result.getString("sortName"));
					courseNames.add(courseName);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return courseNames;
	}


		public List<WillingnessRelocate> getWillingnessRelocates() {
			List<WillingnessRelocate> willingnessRelocates= new ArrayList<WillingnessRelocate>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT name FROM willingnessrelocate";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						WillingnessRelocate willingnessRelocate = new WillingnessRelocate();
						willingnessRelocate.setName(result.getString("name"));
						willingnessRelocates.add(willingnessRelocate);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return willingnessRelocates;
		}


		public List<DriveAttending> getDriveAttending() {
			List<DriveAttending> driveAttendings= new ArrayList<DriveAttending>();
			try (Connection connection = datasource.getConnection()) {
				String getSql = "SELECT name FROM driveattending";
				try (PreparedStatement statement = connection.prepareStatement(getSql)) {
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						DriveAttending driveAttending = new DriveAttending();
						driveAttending.setName(result.getString("name"));
						driveAttendings.add(driveAttending);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return driveAttendings;
		}
		
		private void transformStudent(ResultSet result, Student student) throws SQLException {
			student.setId(result.getLong(1));
			student.setEmail(result.getString("email"));
			student.setEmployeeName(result.getString("employeeName"));
			student.setEmployeeId(result.getString("employeeId"));
			student.setCandidateName(result.getString("candidateName"));
			
			student.setCandidateEmail(result.getString("candidateEmail"));
			student.setDob(result.getDate("dob"));
			student.setContactNo(result.getString("contactNo"));
			student.setGender(result.getLong("gender"));
			student.setGenderName(result.getString("genderName"));
			student.setNationality(result.getString("nationality"));
			
			student.setCurrentLocation(result.getString("currentLocation"));
			student.setCountry(result.getLong("country"));
			student.setCountryName(result.getString("countryName"));
			student.setState(result.getLong("state"));
			student.setStateName(result.getString("stateName"));
			student.setCity(result.getLong("city"));
			student.setCityName(result.getString("cityName"));
			student.setPincode(result.getString("pincode"));
			
			student.setPrefferedLocation(result.getString("prefferedLocation"));
			student.setLocationName(result.getString("locationName"));
			student.setCollegeName(result.getString("collegeName"));
			student.setCourseName(result.getLong("courseName"));
			student.setBranchName(result.getString("branchName"));
			student.setYear(result.getString("year"));
			student.setScoreNineteen(result.getString("scoreNineteen"));
			
			student.setScoreTwenty(result.getString("scoreTwenty"));
			student.setTwelveMark(result.getString("twelveMark"));
			student.setTenMark(result.getString("tenMark"));
			student.setWillingnessRelocate(result.getLong("willingnessRelocate"));
			student.setWillingnessRelocateName(result.getString("willingnessRelocateName"));
			student.setDriveAttending(result.getLong("driveAttending"));
			student.setDriveAttendingName(result.getString("driveAttendingName"));
			student.setFileName(result.getString("fileName"));
			//student.setResume(result.getBlob("resume"));
			}
//		student.setFileName(result.getString("fileName"));
		//student.setResume(result.getBlob("resume"));
		private void prepareStatement(PreparedStatement statement, Student student) throws SQLException {
			
			statement.setString(1, student.getEmail());
			statement.setString(2, student.getEmployeeName());
			statement.setString(3, student.getEmployeeId());
			statement.setString(4, student.getCandidateName());
			statement.setString(5, student.getCandidateEmail());
			statement.setDate(6,(Date) student.getDob());
			statement.setString(7, student.getContactNo());
			statement.setLong(8, student.getGender());
			statement.setString(9, student.getGenderName());
			statement.setString(10, student.getNationality());
			statement.setString(11, student.getCurrentLocation());
			statement.setLong(12, student.getCountry());
			statement.setString(13, student.getCountryName());
			statement.setLong(14, student.getState());
			statement.setString(15, student.getStateName());
			statement.setLong(16, student.getCity());
			statement.setString(17, student.getCityName());
			statement.setString(18, student.getPincode());
			statement.setString(19, student.getPrefferedLocation());
			statement.setString(20, student.getLocationName());
			statement.setString(21, student.getCollegeName());
			statement.setLong(22, student.getCourseName());
			statement.setString(23, student.getBranchName());
			statement.setString(24,student.getYear());
			statement.setString(25, student.getScoreNineteen());
			statement.setString(26, student.getScoreTwenty());
			statement.setString(27, student.getTwelveMark());
			statement.setString(28, student.getTenMark());
			statement.setLong(29, student.getWillingnessRelocate());
			statement.setString(30, student.getWillingnessRelocateName());
			statement.setLong(31, student.getDriveAttending());
			statement.setString(32, student.getDriveAttendingName());
			statement.setString(33,student.getFileName());
			//statement.setBlob(34,student.getResume());
		}
	//		statement.setString(25,student.getFileName());
		//
		
		
		
}

	







