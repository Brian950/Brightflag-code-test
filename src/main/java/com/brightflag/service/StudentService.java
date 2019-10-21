package com.brightflag.service;

import java.util.List;

import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightflag.domain.Student;
import com.brightflag.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> getStudents() {
		return studentRepository.getStudents();
	}

	public List<Student> getStudentsEnrolled(Integer subjectID){ return studentRepository.getStudentsEnrolled(subjectID); }
}
