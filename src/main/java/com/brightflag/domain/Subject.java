package com.brightflag.domain;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private Integer subjectID;
	private String subjectName;

	private List<Student> students;

	public Subject(){ students = new ArrayList<Student>(); }

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
	}

}
