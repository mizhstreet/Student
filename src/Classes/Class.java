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
public class Class {
    private int classID;
    private String name;
    private String description;
    private boolean status;

    public Class() {
    }

    public Class(int classID, String name, String description, boolean status) {
        this.classID = classID;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    
    
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
