$(document).ready(function(){
	
	var searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has("id")){
		getStudentDetails(searchParams.get("id"));
	}
	
		
  $("#saveStudent").click(function(){
	  

	  var studentId = $("#studentId").val();
	  var email = $("#email").val();
	  var employeeName = $("#employeeName").val();
	  var employeeId = $("#employeeId").val();
	  var candidateName = $("#candidateName").val();
	 
	  var candidateEmail = $("#candidateEmail").val();
	  var dob = $("#dob").val();
	  var contactNo = $("#contactNo").val();
	  var gender =  $("input[name='gender']:checked").val();
	  //var genderName=$("#input[name='gender']:checked").val();
	  var nationality = $("#nationality").val();
	  
	  var currentLocation = $("#currentLocation").val();
	  var country = $("#country-dropdown").val();
	  var state = $("#state-dropdown").val();
	  var city = $("#city-dropdown").val();
	  var pincode = $("#pincode").val();
	  
	  var prefferedLocation =  $("input[name='prefferedlocation']:checked").val();
	  var collegeName = $("#collegeName").val();
	  var courseName =  $("input[name='coursename']:checked").val();
	  var year = $("#year").val();
	  var scoreNineteen = $("#scoreNineteen").val();
	  
	  var scoreTwenty = $("#scoreTwenty").val();
	  var twelveMark = $("#twelveMark").val();
	  var tenMark = $("#tenMark").val();
	  var willingnessRelocate = $("input[name='willingnessrelocate']:checked").val();
	  var driveAttending = $("input[name='driveattending']:checked").val();
	  
	  var fileName = $("#fileName").val();
	 
	  console.log("hello in save function",$("#country-dropdown").val());
	  //var gender_fk =  $("input[name='gender']:checked").val();
	 // console.log("gender",gender);
	  
	  
	  var data = {
			  "email":email,
				"employeeName":employeeName,
				"employeeId":employeeId,
				"candidateName":candidateName,
				"candidateEmail":candidateEmail,
				
				"dob":dob,
				"contactNo":contactNo,
				"gender":gender,
				//"genderName":genderName,
				"nationality":nationality,
				"currentLocation":currentLocation,
				
				"country":country,
				"state":state,
				"city":city,
				"pincode":pincode,
				"prefferedLocation":prefferedLocation,
				
				"collegeName":collegeName,
				"courseName":courseName,
				"year":year,
				"scoreNineteen":scoreNineteen,
				"scoreTwenty":scoreTwenty,
				
				"twelveMark":twelveMark,
				"tenMark":tenMark,
				"willingnessRelocate":willingnessRelocate,
				"driveAttending":driveAttending,
				"fileName":fileName,
				
				
				}
	  if(studentId){
		  updateStudent(data,studentId)
	  }else{
		  saveStudent(data);
	  }
  });
  
  function saveStudent(student){
	  var formData = new FormData();
	  var file = $("#resume")[0].files[0];
	 console.log("file",file);
	  console.log("student",student);
	  formData.append("file",file);
	  formData.append("student",new Blob([JSON.stringify(student)], {
		  	type:"application/json"
	  }));
	  var saveData = $.ajax({
	      type: 'POST',
	      url: "/api/student",
	      data: formData,
	      contentType: false,
	      processData: false,
	      
	      success: function(data){
	    	 $("#studentId").val(data.id);
	    	 window.location.href = "index.html";
	      },
	      error: function(data){
	    	  console.log("failed");
	      }
	});
  }
  
  function updateStudent(student,id){
	  student["id"] = id;
	  console.log("in update ",student);
	  var saveData = $.ajax({
	      type: 'PUT',
	      url: "/api/student",
	      data: JSON.stringify(student),
	      contentType: "application/json",
	      dataType: "json",
	      success: function(data){ 
	    	 console.log("update success!!!",data);
	    	 window.location.href = "index.html"
	      },
	      error: function(data){
	    	  console.log("failed");
	    	  
	      }
	});
  }
  
  
	
  function getStudentDetails(studentId) 
  {
		var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/"+studentId,
			dataType : "json",
			success : function(data) {
				loadStudent(data);
				
				$("#saveStudent").append('</ul>');
						
					},
						error: function(data){
				console.log("error in fetching data!");
			}
		});
}
	
  
  function getCountries(){
	  console.log("in get countries");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/countries",
			dataType : "json",
			success : function(data) {
				console.log("countries data",data);
				var option = "<option value=''></option>";
				var options = "";
                $.each(data,function(index,country){
                	options += "<option value='"+country.id+"' >" + country.name + " (" + country.sortName + ")</option>" 
                });
               $("#country-dropdown").append(option+options);
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
  
  
  function getStates(){
	  console.log("in get sttes");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/states",
			dataType : "json",
			success : function(data) {
				console.log("states data",data);
				var option = "<option value=''></option>";
				var options = "";
                $.each(data,function(index,state){
                	options += "<option value='"+state.id+"' >" + state.name + " </option>" 
                });
               $("#state-dropdown").append(option+options);
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
  
  function getCities(){
	  console.log("in get cities");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/cities",
			dataType : "json",
			success : function(data) {
				console.log("cities data",data);
				var option = "<option value=''></option>";
				var options = "";
                $.each(data,function(index,city){
                	options += "<option value='"+city.id+"' >" + city.name + "</option>" 
                });
               $("#city-dropdown").append(option+options);
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
  
  function getGender(){
	  console.log("in get genders");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/genders",
			dataType : "json",
			success : function(data) {
				var genders = "";
				console.log("gender",data); 
                $.each(data,function(index,gender){
                	var checked = (gender.name=="Male")?"checked":"";
                	genders += "<div class='form-radio-item'>"+
                    "<input type='radio' name='gender' value="+gender.id+" id='"+gender.name+"' "+checked+">"+
                    "<label for='"+gender.name+"' >"+gender.name+"<br></label>"+
                    "<span class='check'></span></div>";
                });
              $("#gender-radio").append(genders);
			
			
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
  function getPrefferedLocations(){
	  console.log("in get Prefferedlocations ");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/prefferedlocations",
			dataType : "json",
			success : function(data) {
				var prefferedlocations = "";
				console.log("prefferedlocations",data); 
                $.each(data,function(index,prefferedlocation){
                	var checked = (prefferedlocation.locationName=="Bengaluru")?"checked":"";
                	prefferedlocations += "<div class='form-radio-item'>"+
                    "<input type='radio' name='prefferedlocation' value="+prefferedlocation.id+" id='"+prefferedlocation.name+"' "+checked+">"+
                    "<label for='"+prefferedlocation.name+"' >"+prefferedlocation.name+"<br></label>"+
                    "<span class='check'></span></div>";
                });
              $("#prefferedlocation-radio").append(prefferedlocations);
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
	  
	  function getPrefferedlocations(){
		  console.log("in get Prefferedlocations");
		  var getData = $.ajax({
				type : 'GET',
				contentType : "application/json",
				url : "/api/student/prefferedlocations",
				dataType : "json",
				success : function(data) {
					var prefferedlocations = "";
					console.log("prefferedlocation",data); 
	                $.each(data,function(index,prefferedlocation){
	                	var checked = (prefferedlocation.name=="Yes")?"checked":"";
	                	prefferedlocations += "<div class='form-radio-item'>"+
	                    "<input type='radio' name='prefferedlocation' value="+prefferedlocation.id+" id='"+prefferedlocation.name+"' "+checked+">"+
	                    "<label for='"+prefferedlocation.name+"' >"+prefferedlocation.name+"<br></label>"+
	                    "<span class='check'></span></div>";
	                });
	              $("#prefferedlocation-radio").append(prefferedlocations);
				
				
				},
				error: function(data){
					console.log("error in fetching data!");
				}
			});
	  }
  }
  function getCourseName(){
	  console.log("in get CourseName ");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/coursenames",
			dataType : "json",
			success : function(data) {
				var coursenames = "";
				console.log("coursenames",data); 
                $.each(data,function(index,coursename){
                	var checked = (coursename.name=="B.E (Computer Science)")?"checked":"";
                	coursenames += "<div class='form-radio-item'>"+
                    "<input type='radio' name='coursename' value="+coursename.id+" id='"+coursename.name+"' "+checked+">"+
                    "<label for='"+coursename.name+"' >"+coursename.name+"<br></label>"+
                    "<span class='check'></span></div>";
                });
              $("#coursename-radio").append(coursenames);
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
  function getWillingnessRelocates(){
	  console.log("in get willingnessRelocate");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/willingnessrelocates",
			dataType : "json",
			success : function(data) {
				var willingnessrelocates = "";
				console.log("willingnessrelocate",data); 
                $.each(data,function(index,willingnessrelocate){
                	var checked = (willingnessrelocate.name=="YES")?"checked":"";
                	willingnessrelocates += "<div class='form-radio-item'>"+
                    "<input type='radio' name='willingnessrelocate' value="+willingnessrelocate.id+" id='"+willingnessrelocate.name+"' "+checked+">"+
                    "<label for='"+willingnessrelocate.name+"' >"+willingnessrelocate.name+"<br></label>"+
                    "<span class='check'></span></div>";
                });
              $("#willingnessrelocate-radio").append(willingnessrelocates);
			
			
			},
			error: function(data){
				console.log("error in fetching data!");
			}
		});
  }
   function getDriveAttendings(){
	  console.log("in get driveAttending");
	  var getData = $.ajax({
			type : 'GET',
			contentType : "application/json",
			url : "/api/student/driveattendings",
			dataType : "json",
			success : function(data) {
				var driveattendings = "";
				console.log("driveattending",data); 
                $.each(data,function(index,driveattending){
                	var checked = (driveattending.name=="Yes")?"checked":"";
                	driveattendings += "<div class='form-radio-item'>"+
                    "<input type='radio' name='driveattending' value="+driveattending.id+" id='"+driveattending.name+"' "+checked+">"+
                    "<label for='"+driveattending.name+"' >"+driveattending.name+"<br></label>"+
                    "<span class='check'></span></div>";
                });
              $("#driveattending-radio").append(driveattendings);
			
			
			},
			error: function(data){
				console.log("error in fetching data!");
			}
	  
		});
   }
  
	//getCountries();
//  getStates();
//  getCities();
//  getGender();
//  getWillingnessRelocates();
//  getDriveAttendings();
//  getPrefferedLocations();
//  getCourseName();
//  
  function loadStudent(data)
  {
	  console.log("data on load",data);
	  $("#studentId").val(data.id);
	  $("#email").val(data.email);
	  $("#employeeName").val(data.employeeName);
	  $("#employeeId").val(data.employeeId);
	  $("#candidateName").val(data.candidateName);
	  
	  $("#candidateEmail").val(data.candidateEmail);
	  $("#dob").val(data.dob);
	  $("#contactNo").val(data.contactNo);
	 $("#gender-radio").val(data.gender); 
	  $("#gender-radio").val(data.genderName);
	  
	  $("#nationality").val(data.nationality);
	  
	  $("#currentLocation").val(data.currentLocation);
	  $("#country-dropdown").val(data.country);
	  $("#country-dropdown").val(data.countryName);
	  $("#state-dropdown").val(data.state);
	  $("#state-dropdown").val(data.stateName);
	  $("#city-dropdown").val(data.city);
	  $("#city-dropdown").val(data.cityName);
	  $("#pincode").val(data.pincode);
	  
	  $("#prefferedlocation-radio").val(data.prefferedLocation);
	  $("#prefferedlocation-radio").val(data.locationName);
	  $("#collegeName").val(data.collegeName);
	  $("#coursename").val(data.courseName);
	  $("#coursename").val(data.branchName);
	  $("#year").val(data.year);
	  $("#scoreNineteen").val(data.scoreNineteen);
	  
	  $("#scoreTwenty").val(data.scoreTwenty);
	  $("#twelveMark").val(data.twelveMark);
	  $("#tenMark").val(data.tenMark);
	  $("#willingnessrelocate-radio").val(data.willingnessRelocate);
	  $("#willingnessrelocate-radio").val(data.willingnessRelocateName);
	  $("#driveattending-radio").val(data.driveAttending);
	  $("#driveattending-radio").val(data.driveAttendingName);
	  
	  $("#fileName").val(data.fileName);
	
  }
 });