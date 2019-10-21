package com.brightflag.codetest;

import com.brightflag.controller.StudentAPIController;
import com.brightflag.domain.Enrollment;
import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	// Check that students can be successfully retrieved using the Controller api
	@Test
	public void testGetStudents(){
		ResponseEntity<List<Student>> response = this.restTemplate.exchange(
				"http://localhost:"+port+"/api/getStudents",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Student>>(){});
		List<Student> students = response.getBody();
		List<Subject> marySubjects = students.get(0).getSubjects();

		// Test response, the first found studentID and if Mary Smith's subjects have been set
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, students.get(0).getStudentID() > 0);
		Assert.assertEquals(true, marySubjects.get(0).getSubjectName().equals("Classics"));
	}

	// Check that subjects can be successfully retrieved using the Controller api
	@Test
	public void testGetSubjects(){
		ResponseEntity<List<Subject>> response = this.restTemplate.exchange(
				"http://localhost:"+port+"/api/getSubjects",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Subject>>(){});
		List<Subject> subjects = response.getBody();
		List<Student> classicsStudents = subjects.get(0).getStudents();

		// Test response, the first subject ID, first subject Name and that the correct student is assigned
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, subjects.get(0).getSubjectID() == 1);
		Assert.assertEquals(true, subjects.get(0).getSubjectName().equals("Classics"));
		Assert.assertEquals(true, classicsStudents.get(0).getFirstName().equals("Mary"));
	}

	// Test enrolling a student in a subject they ARE NOT already enrolled in
	@Test
	public void testValidEnrollment() throws URISyntaxException{

		String targetUrl = "http://localhost:"+port+"/api/";
		URI uri = new URI(targetUrl);

		Enrollment enrollment = new Enrollment(2, 1);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<Enrollment> request = new HttpEntity <>(enrollment, headers);

		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, response.getBody().equals("{\"result\":\"ENROLLMENT_SUCCESS\"}"));

	}

	// Test enrolling a student in a subject they ARE already enrolled in
	@Test
	public void testInvalidEnrollment() throws URISyntaxException {
		String targetUrl = "http://localhost:"+port+"/api/";
		URI uri = new URI(targetUrl);

		Enrollment enrollment = new Enrollment(1, 2);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<Enrollment> request = new HttpEntity <>(enrollment, headers);

		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals(true, response.getBody().equals("{\"result\":\"ALREADY_ENROLLED\"}"));
	}

}
