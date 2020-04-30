package com.ancestry.Ultron.model;



import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="student_details")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

   	String email;
	String employeeName;
	String employeeId;
	
	@NotBlank
	@Email(message = "Please enter a valid e-mail address")
	String candidateName;
	@Size(min = 3, max = 50)
	String candidateEmail;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	Date dob;
	
	String contactNo;
	
	@Column(name = "gender")
	@NotBlank
	Long gender;
	String nationality;
	String currentLocation;
	
	@NotBlank
	@Column(name = "country")
	Long country;
	
	@NotBlank
	@Column(name = "state")
	Long state;
	
	@NotBlank
	@Column(name = "city")
	Long city;
	String pincode;

	@NotBlank
	@Column(name = "prefferedLocation")
	String prefferedLocation;
	
	@NotBlank
	String collegeName;
	
	@NotBlank
	@Column(name = "courseName")
	Long courseName;
	@NotBlank
	String year;
	String scoreNineteen;
	String scoreTwenty;
	@NotBlank
	String twelveMark;
	@NotBlank
	String tenMark;

	@NotBlank
	@Column(name = "willingnessRelocate")
	Long willingnessRelocate;

	@NotBlank
	@Column(name = "driveAttending")
	Long driveAttending;
	
		String fileName;
		
		Blob resume;
		
		String genderName;
		String countryName;
		String stateName;
		String cityName;
		String branchName;
		String locationName;
		String willingnessRelocateName;
		String driveAttendingName;
		
		

	public String getGenderName() {
			return genderName;
		}

		public void setGenderName(String genderName) {
			this.genderName = genderName;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String satteName) {
			this.stateName = satteName;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getBranchName() {
			return branchName;
		}

		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}

		public String getLocationName() {
			return locationName;
		}

		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}

		public String getWillingnessRelocateName() {
			return willingnessRelocateName;
		}

		public void setWillingnessRelocateName(String willingnessRelocateName) {
			this.willingnessRelocateName = willingnessRelocateName;
		}

		public String getDriveAttendingName() {
			return driveAttendingName;
		}

		public void setDriveAttendingName(String driveAttendingName) {
			this.driveAttendingName = driveAttendingName;
		}

	public Blob getResume() {
			return resume;
		}

		public void setResume(Blob resume) {
			this.resume = resume;
		}

	public Long getGender() {
			return gender;
		}

		public void setGender(Long gender) {
			this.gender = gender;
		}

		public Long getCountry() {
			return country;
		}

		public void setCountry(Long country) {
			this.country = country;
		}

		public Long getState() {
			return state;
		}

		public void setState(Long state) {
			this.state = state;
		}

		public Long getCity() {
			return city;
		}

		public void setCity(Long city) {
			this.city = city;
		}

		public String getPrefferedLocation() {
			return prefferedLocation;
		}

		public void setPrefferedLocation(String prefferedLocation) {
			this.prefferedLocation = prefferedLocation;
		}

		public Long getCourseName() {
			return courseName;
		}

		public void setCourseName(Long courseName) {
			this.courseName = courseName;
		}

		public Long getWillingnessRelocate() {
			return willingnessRelocate;
		}

		public void setWillingnessRelocate(Long willingnessRelocate) {
			this.willingnessRelocate = willingnessRelocate;
		}

		public Long getDriveAttending() {
			return driveAttending;
		}

		public void setDriveAttending(Long driveAttending) {
			this.driveAttending = driveAttending;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	

	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getScoreNineteen() {
		return scoreNineteen;
	}

	public void setScoreNineteen(String scoreNineteen) {
		this.scoreNineteen = scoreNineteen;
	}

	public String getScoreTwenty() {
		return scoreTwenty;
	}

	public void setScoreTwenty(String scoreTwenty) {
		this.scoreTwenty = scoreTwenty;
	}

	public String getTwelveMark() {
		return twelveMark;
	}

	public void setTwelveMark(String twelveMark) {
		this.twelveMark = twelveMark;
	}

	public String getTenMark() {
		return tenMark;
	}

	public void setTenMark(String tenMark) {
		this.tenMark = tenMark;
	}

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
		

}
