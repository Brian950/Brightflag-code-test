package com.brightflag.repository;

import com.brightflag.domain.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String enrollStudent(Enrollment model){
        int result = jdbcTemplate.update("INSERT INTO enrollment(enrollmentID, studentID, subjectID) " +
                "VALUES(default, "+ model.getStudentID() +", "+ model.getSubjectID() +")");
        return "ENROLLMENT_SUCCESS";
    }

}
