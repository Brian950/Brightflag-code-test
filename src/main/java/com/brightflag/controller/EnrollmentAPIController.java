package com.brightflag.controller;

import com.brightflag.domain.Enrollment;
import com.brightflag.service.EnrollmentService;
import com.brightflag.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentAPIController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("/api")
    public String enrollStudent(@RequestBody Enrollment model) {
        String result = enrollmentService.enrollStudent(model);
        return "{\"result\":\""+ result +"\"}";
    }

}