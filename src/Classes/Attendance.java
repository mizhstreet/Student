/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author mbiuu
 */
public class Attendance {
    private int studentId;
    private boolean attendance;
    private String remark;
    private int sessionId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isAttendant() {
        return attendance;
    }

    public void setAttendant(boolean attendance) {
        this.attendance = attendance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    
}
