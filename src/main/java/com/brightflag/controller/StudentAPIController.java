package com.brightflag.controller;

import java.util.List;

import com.brightflag.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.domain.Student;
import com.brightflag.service.StudentService;

@RestController
public class StudentAPIController {

	@Autowired
	StudentService studentService;
	@Autowired
	SubjectService subjectService;

	@RequestMapping("api/getStudents")
	public List<Student> getStudents() {
		List<Student> students = studentService.getStudents();

		for(Student s: students){
			s.setSubjects(subjectService.getStudentSubjects(s.getStudentID()));
		}

		return students;
	}

}
