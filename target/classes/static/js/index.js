$(document).ready(function() {
	console.log("Inside List Page...");
	

	
function getStudentsDetails() {
	var getData = $.ajax({
		type : 'GET',
		contentType : "application/json",
		url : "/api/student",
		dataType : "json",
		success : function(data) {
			loadTableData(data);
			console.log("get data");
		},
		error: function(data){
			console.log("error in fetching data!");
		}
	});
}


$( "#duplicateStudent" ).click(function() {
	  duplicateStudent();
	  return false;
});


function duplicateStudent() {
	var getData = $.ajax({
		type : 'GET',
		contentType : "application/json",
		url : "/api/student/duplicate",
		dataType : "json",
		success : function(data) {
			console.log("duplicate data");
			$("#studentList").empty();
			loadTableData(data);
		
		},
		error: function(data){
			console.log("error in fetching data!");
		}
	});
}
function loadTableData(studentList){
	if(studentList.length>0){
		$("#noStudents").hide();
		var rows = "";
		
		$.each(studentList,function(index,student){
			var row = "<tr id='row_"+student.id+"'>";
			row += "<td>" + student.email + "</td>";
			row += "<td>" + student.employeeName + "</td>";
			row += "<td>" + student.employeeId + "</td>";
			row += "<td>" + student.candidateName + "</td>";
			row += "<td>" + student.candidateEmail + "</td>";
			row += "<td>" + student.dob + "</td>";
			row += "<td>" + student.contactNo + "</td>";
			//row += "<td>" + student.gender + "</td>";
			row += "<td>" + student.genderName + "</td>";
			row += "<td>" + student.nationality + "</td>";
			row += "<td>" + student.currentLocation + "</td>";
			//row += "<td>" + student.coutry + "</td>";
			row += "<td>" + student.coutryName + "</td>";
			//row += "<td>" + student.state + "</td>";
			row += "<td>" + student.stateName + "</td>";
			//row += "<td>" + student.city + "</td>";
			row += "<td>" + student.cityName + "</td>";
			row += "<td>" + student.pincode + "</td>";
			//row += "<td>" + student.prefferedLocation + "</td>";
			row += "<td>" + student.locationName + "</td>";
			row += "<td>" + student.collegeName + "</td>";
			//row += "<td>" + student.courseName + "</td>";
			row += "<td>" + student.branchName + "</td>";
			row += "<td>" + student.year + "</td>";
			row += "<td>" + student.scoreNineteen + "</td>";
			row += "<td>" + student.scoreTwenty + "</td>";
			row += "<td>" + student.twelveMark + "</td>";
			row += "<td>" + student.tenMark + "</td>";
			//row += "<td>" + student.willingnessRelocate + "</td>";
			row += "<td>" + student.willingnessRelocateName + "</td>";
			//row += "<td>" + student.driveAttending + "</td>";
			row += "<td>" + student.driveAttendingName + "</td>";
			row += "<td>" + student.fileName + "</td>";
						
			row += "<td><a class='btn btn-primary updateStudent' id='update_"+student.id+"'><i class='fas fa-user-edit ml-2'></i></a></td>";
			row += "<td><a class='btn btn-primary deleteStudent' id='delete_"+student.id+"'><i class='fas fa-user-times ml-2'></i></a></td>";
			row += "</tr>"
				
			rows += row;	
		});
		
		$("#studentList").append(rows);
	}
	$(document).on("click",".deleteStudent",function(){
		var id = $(this).attr("id");
		var studentId = id.split("_")[1];
		deleteStudent(studentId);
	});
	
	$(document).on("click",".updateStudent",function(){
		var id = $(this).attr("id");
		var studentId = id.split("_")[1];
		console.log("on update student: ",studentId);
		window.location.href = "addstudent.html?id="+studentId;
	});
	

	function deleteStudent(studentId){
		var deleteStudent = $.ajax({
			type : 'DELETE',
			url : "/api/student/"+studentId,
			success : function() {
				console.log("in delete success");
				$("#row_"+studentId).remove();
			},
			error:function(){
				console.log("error in delete");
			}
		});
	}
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
//updateStudent();

	// Why: To display all the student details.
	getStudentsDetails();
	//duplicateStudent();
	
});

