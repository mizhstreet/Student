/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;

/**
 *
 * @author mbiuu
 */
public class Student {
    private String studentId;
    private String fname;
    private String lname;
    private String phone;
    private Date dob;
    private Date joinedDate;
    private String address;   
    private boolean status;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDob() {
        return dob;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
