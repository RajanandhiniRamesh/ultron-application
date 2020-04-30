
package com.ancestry.Ultron.studentdao;

import java.util.List;

import com.ancestry.Ultron.model.Student;



public interface StudentDao {
	
		public void add(Student student);
		public void edit(Student student);
		public void delete(int id);
		public Student getElementById(Long id);
		public List duplicate();
		public List getAllStudents();
		public List getAllStudentsFK();

	}


