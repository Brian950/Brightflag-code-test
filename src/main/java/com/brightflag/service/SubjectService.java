package com.brightflag.service;

import com.brightflag.domain.Subject;
import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getSubjects() { return subjectRepository.getSubjects(); }

    public List<Subject> getStudentSubjects(int studentID) { return subjectRepository.getStudentSubjects(studentID); }

}
