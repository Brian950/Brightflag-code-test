package com.brightflag.repository;

import com.brightflag.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Subject> getSubjects(){
        return jdbcTemplate.query("SELECT subjectID, subjectName from subject",
                new BeanPropertyRowMapper<Subject>(Subject.class));
    }

    public List<Subject> getStudentSubjects(Integer studentID){
        return jdbcTemplate.query("SELECT su.subjectID, su.subjectName FROM subject AS su\n" +
                        "INNER JOIN enrollment AS e ON e.subjectID = su.subjectID\n" +
                        "INNER JOIN student AS s ON s.studentID = e.studentID\n" +
                        "WHERE e.studentID = " + studentID,
                new BeanPropertyRowMapper<Subject>(Subject.class));
    }
}
