package com.brightflag.repository;

import java.util.List;

import com.brightflag.domain.Enrollment;
import com.brightflag.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Student;

@Repository
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Student> getStudents() {
		return jdbcTemplate.query("SELECT studentID, firstName, lastName FROM student;",
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> getStudentsEnrolled(Integer subjectID){
		return jdbcTemplate.query("SELECT s.studentID, s.firstName, s.lastName FROM student AS s\n" +
						"INNER JOIN enrollment AS e ON e.studentID = s.studentID\n" +
						"INNER JOIN subject AS su ON su.subjectID = e.subjectID\n" +
						"WHERE e.subjectID = " + subjectID,
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public boolean isStudentEnrolled(Enrollment model){
		List<Student> students = jdbcTemplate.query("SELECT s.studentID, s.firstName, s.lastName FROM student AS s\n" +
						"INNER JOIN enrollment AS e ON e.studentID = s.studentID\n" +
						"INNER JOIN subject AS su ON su.subjectID = e.subjectID\n" +
						"WHERE e.studentID = " + model.getStudentID() + " AND e.subjectID = " + model.getSubjectID(),
				new BeanPropertyRowMapper<Student>(Student.class));

		if(students.size() > 0)
			return true;
		else
			return false;
	}

}
