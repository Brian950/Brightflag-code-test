package com.brightflag.service;

import com.brightflag.domain.Enrollment;
import com.brightflag.repository.EnrollmentRepository;
import com.brightflag.repository.StudentRepository;
import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;

    public String enrollStudent(Enrollment model){
        if(isEnrolled(model))
            return "ALREADY_ENROLLED";
        else{
            return enrollmentRepository.enrollStudent(model);
        }
    }

    public boolean isEnrolled(Enrollment model){
        return studentRepository.isStudentEnrolled(model);
    }

}
