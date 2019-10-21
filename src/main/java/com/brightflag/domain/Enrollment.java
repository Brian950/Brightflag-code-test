package com.brightflag.domain;

public class Enrollment {

    private Integer studentID;
    private Integer subjectID;

    public Enrollment(Integer stuID, Integer subID){
        studentID = stuID;
        subjectID = subID;
    }


    public Integer getSubjectID() { return subjectID; }

    public void setSubjectID(Integer subjectID) { this.subjectID = subjectID; }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

}
