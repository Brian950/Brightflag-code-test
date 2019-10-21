package com.brightflag.domain;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private Integer studentID;
	private String firstName;
	private String lastName;

	// Lists for the Students Subjects and Exams
	private List<Subject> subjects;
	private List<Exam> exams;

	public  Student(){
		subjects = new ArrayList<Subject>();
		exams = new ArrayList<Exam>();
	}

	// Set all the students' subjects
	public void setSubjects(List<Subject> subs){
		this.subjects = subs;
	}

	// Add a subject to the student
	public void addSubject(Subject s){
		subjects.add(s);
	}

	// Remove a subject from the student
	public void removeSubject(Subject s){
		subjects.remove(s);
	}

	// Get the student's subjects
	public List<Subject> getSubjects(){ return subjects; }

	// Add an exam
	public void addExam(Exam e){
		exams.add(e);
	}

	// Remove and exam
	public void removeExam(Exam e){
		exams.remove(e);
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
