/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.tn.controllers;

import co.com.tn.models.Attachment;
import co.com.tn.models.Person;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;


/**
 *
 * @author Usuario
 */
@ManagedBean(name="attachmentController")
@SessionScoped
public class AttachmentController implements Serializable{

    private static final long serialVersionUID = 1L;
    
    ArrayList<Attachment> files = new ArrayList<>(Arrays.asList(
            new Attachment(1, "Name 1", "Dependence", new Date(), ""),
            new Attachment(2, "Name 1", "Dependence", new Date(), ""),
            new Attachment(3, "Name 1", "Dependence", new Date(), ""),
            new Attachment(4, "Name 1", "Dependence", new Date(), ""),
            new Attachment(5, "Name 1", "Dependence", new Date(), ""),
            new Attachment(6, "Name 1", "Dependence", new Date(), ""),
            new Attachment(7, "Name 1", "Dependence", new Date(), ""),
            new Attachment(8, "Name 1", "Dependence", new Date(), ""),
            new Attachment(9, "Name 1", "Dependence", new Date(), ""),
            new Attachment(10, "Name 1", "Dependence", new Date(), ""))
    );
    
    Attachment fileToUpdate = new Attachment(); //file searched and found
    Attachment fileCreated = new Attachment(); 
    private Part uploadedFile;
    private String fileContent;
    String nameToCreate;
    String dependenceToCreate;
    String dateToCreate;
    
    int idUpload;
    String nameUpdate;
    String dependenceUpdate;
    String dateToUptade;
    int id = 10;
   
     
     
     
     public String findFileById(Attachment file){
     
         //TODO: se debe buscar en BD
//            fileToUpdate.setId(file.getId());
//            fileToUpdate.setName("fileFound");
//            fileToUpdate.setDependence("dependenceFound");
//            fileToUpdate.setDate(new Date());
//            
            file.setEditable(true);
            return null;
     }
     
     public String updateDataFiles() throws ParseException{
         
         //TODO: Actualizar en BD
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         
         for(Attachment attachment : files){
             attachment.setEditable(false);
             if (idUpload == attachment.getId()) {
                 Attachment includeFile = new Attachment();
                 includeFile.setId(attachment.getId());
                 includeFile.setName(nameUpdate);
                 includeFile.setDependence(dependenceUpdate);
                 Date date = formatter.parse(dateToUptade);
                 includeFile.setDate(date);
                 includeFile.setContentJson(attachment.getContentJson());
                 
                 files.remove(attachment);
                 files.add(includeFile);
                 return "index";
             }    
             
         }
         
         return null;
     
     } 
     
     public String deleteFile(int id){
         
         for (Attachment file : files) {
             if (id == file.getId()) {
                 Attachment fileToDelete = file;
                 files.remove(fileToDelete);
                      return "index";
             } else {
                 System.out.println("No se encontro el archivo");
             }
         }
          
         return null;
     }
     
     
      public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        Part file = (Part)value;
        if (file.getSize() > 1024) {
            msgs.add(new FacesMessage("El archivo es demaciado grande"));
        }
        if (!"text/plain".equals(file.getContentType()) && !"application/json".equals(file.getContentType())) {
            msgs.add(new FacesMessage("El archivo no es tipo txt o .json"));
        }
        
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public String uploadFile() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Gson g = new Gson();
        
        try {
            int idCount = id+1;
            fileContent = new Scanner(uploadedFile.getInputStream()).useDelimiter("\\A").next();
            Person p = g.fromJson(fileContent, Person.class);
            String contentJson = g.toJson(p);
            Date date = formatter.parse(dateToCreate);
            Attachment savedFile = new Attachment(idCount, nameToCreate, dependenceToCreate, date, contentJson);
            files.add(savedFile);
            
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                "error al cargar el archivo",
                                                null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
           catch(JsonSyntaxException e){
            System.err.println(e);       
        }
          
        return "index";
       //TODO: Enviar a la BD ES CREATE
    }
    
    
     
     
     
    public ArrayList<Attachment> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Attachment> files) {
        this.files = files;
    }

    public Attachment getFileToUpdate() {
        return fileToUpdate;
    }

    public void setFileToUpdate(Attachment fileToUpdate) {
        this.fileToUpdate = fileToUpdate;
    }

    public Attachment getFileCreated() {
        return fileCreated;
    }

    public void setFileCreated(Attachment fileCreated) {
        this.fileCreated = fileCreated;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getNameToCreate() {
        return nameToCreate;
    }

    public void setNameToCreate(String fileNameToCreate) {
        this.nameToCreate = fileNameToCreate;
    }

    public String getDependenceToCreate() {
        return dependenceToCreate;
    }

    public void setDependenceToCreate(String dependenceToCreate) {
        this.dependenceToCreate = dependenceToCreate;
    }

    public String getDateToCreate() {
        return dateToCreate;
    }

    public void setDateToCreate(String dateToCreate) {
        this.dateToCreate = dateToCreate;
    }

    public int getIdUpload() {
        return idUpload;
    }

    public void setIdUpload(int idUpload) {
        this.idUpload = idUpload;
    }

    public String getNameUpdate() {
        return nameUpdate;
    }

    public void setNameUpdate(String nameUpdate) {
        this.nameUpdate = nameUpdate;
    }

    public String getDependenceUpdate() {
        return dependenceUpdate;
    }

    public void setDependenceUpdate(String dependenceUpdate) {
        this.dependenceUpdate = dependenceUpdate;
    }

    public String getDateToUptade() {
        return dateToUptade;
    }

    public void setDateToUptade(String dateToUptade) {
        this.dateToUptade = dateToUptade;
    }
    
    

    
   
     
     
     
     
}
