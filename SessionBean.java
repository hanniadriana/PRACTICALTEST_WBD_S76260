/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.SessionBean to edit this template
 */
package com.lab.bean;
package com.lab.bean;

public class SessionBean implements java.io.Serializable {
    private String session_id;
    private String student_name;
    private String branch_location;
    private String lesson_type;
    private String status;

    public SessionBean() {}

    public String getsession_id() { return session_id; }
    public void setsession_id(String session_id) { this.session_id =session_id; }

    public String getstudent_name() { return student_name; }
    public void setstudent_name(String student_name) { this.student_name = student_name; }

    public String getbranch_location() { return branch_location; }
    public void setbranch_location(String branch_location) { this.branch_location = branch_location; }

    public String getlesson_type() { return lesson_type; }
    public void setlesson_type(String lesson_type) { this.lesson_type = lesson_type; }
    
    public String status() { return status; }
    public void setstatus(String status) { this.status = status; }
    
}