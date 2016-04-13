/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;


/**
 *
 * @author mac_f
 */
public class Database {
    double latRad = 51.988431*Math.PI/180;
    double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
    double null_ylat = (10159/2)-(10563*mercN/(2*Math.PI));
    double null_xlong = (4.396787 + 180.0) * (10563 / 360);  
    
    List<Garage> list_garages = new ArrayList();
 
    Image P_image = new Image("file:Images/P-location_image.png");  
    Image M_image = new Image("file:Images/M-location_image.png"); 
    Image T_image = new Image("file:Images/T-location_image.png"); 
    Image B_image = new Image("file:Images/B-location_image.png"); 
    public Database(){
    
    } 
    public List<Garage> getGaragelist(){
                    try
                {
                    Class.forName("org.postgresql.Driver");
                    
                    Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ParknTravel","postgres","password");
                    if(con!=null)
                        System.out.println("Connected");
                    Statement st=con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM parking;");
                    while (rs.next()){ //loopt door de lijst tot er niks meer is
                        Double longitude_sql = rs.getDouble("longitude"); //pakt de volgende long
                        Double latitude_sql = rs.getDouble("latitude"); //pakt de volgende lat
                        String name = rs.getString("name"); //pakt de volgende name
                    // calculate de x y van de long lat
                  
                        double latRad1 = latitude_sql*Math.PI/180;
                        double mercN1 = Math.log(Math.tan((Math.PI/4)+(latRad1/2)));
                        double sql_ylat = (10159/2)-(10563*mercN1/(2*Math.PI));
                        double sql_xlong = (longitude_sql + 180.0) * (10563 / 360);  
                        // calculate the locatie van x y
                        double pointysql = ((sql_ylat - null_ylat)*1781);
                        double pointxsql = ((sql_xlong - null_xlong)*1758); 
                        // tekent de P op de locatie
                        Garage garage = new Garage(P_image, name, "Test","Not test", pointxsql,pointysql);
                        list_garages.add(garage);              
                         // de loop begint opnieuw
                    
                }}
                    catch(Exception ee) // dit is nodig bij een try
                {
                    ee.printStackTrace();
                };  
                return list_garages;    
}}
