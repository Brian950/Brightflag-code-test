package com.brightflag.controller;

import com.brightflag.domain.Subject;
import com.brightflag.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brightflag.service.SubjectService;

import java.util.List;

@RestController
public class SubjectAPIController {

	@Autowired
	SubjectService subjectService;
	@Autowired
	StudentService studentService;

	// Get subjects for a student
	@RequestMapping(value="api/getSubjects", method = RequestMethod.GET)
	public List<Subject> getSubjects() {
		List<Subject> subjects = subjectService.getSubjects();

		for(Subject s: subjects){
			s.setStudents(studentService.getStudentsEnrolled(s.getSubjectID()));
		}
		return subjects;
	}

}
