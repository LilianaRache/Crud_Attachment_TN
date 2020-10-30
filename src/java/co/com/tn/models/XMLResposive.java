/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.tn.models;

/**
 *
 * @author Usuario
 */
public class XMLResposive {
    
    String xml ="<Person>\n" +
"        <idPerson>:idPerson:</idPerson>\n" +
"        <name>:name:</name>\n" +
"        <email>:email:</email>\n" +
"        <dependence>:dependence:</dependence>\n" +
"    </Person>";

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    
  
}
