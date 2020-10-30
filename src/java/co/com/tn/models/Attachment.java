/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.tn.models;

import java.util.Date;

/**
 *
 * @author Liliana
 */
public class Attachment {
    
    int Id;
    String name;
    String dependence;
    Date date;
    boolean editable;
    String contentJson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDependence() {
        return dependence;
    }

    public void setDependence(String dependence) {
        this.dependence = dependence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getContentJson() {
        return contentJson;
    }

    public void setContentJson(String contentJson) {
        this.contentJson = contentJson;
    }
    
    
    
    
    
    
    public Attachment(){
    
    }

    public Attachment(int Id, String name, String dependence, Date date, String contentJson) {
        this.Id = Id;
        this.name = name;
        this.dependence = dependence;
        this.date = date;
        this.contentJson = contentJson;
    }
   
    
}

