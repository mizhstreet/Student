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
    private int attendID;
    private int sessionId;
    private boolean marked;
    private int ClassID;

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }
    
    public int getAttendID() {
        return attendID;
    }

    public void setAttendID(int attendID) {
        this.attendID = attendID;
    }
    
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    
}
