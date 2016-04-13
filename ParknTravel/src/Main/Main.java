
package Main;

// Commit test Selim Esengin 11:20

import Main.Stations.Bus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {
    
    int map_x = -3500;
    int map_y = -5400;
    int canvas_x = 1000;
    int canvas_y = 1500;
    
   //---------------------------------------------------------//
   //              berekenen van null punt x en y             //
   //---------------------------------------------------------//    
    double latRad = 51.988431*Math.PI/180;
    double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
    double null_ylat = (10159/2)-(10563*mercN/(2*Math.PI));
    double null_xlong = (4.396787 + 180.0) * (10563 / 360);  


    Image background = new Image("file:Images/mapRotterdam.png");
    Image up_image = new Image("file:Images/up_button.png");
    Image down_image = new Image("file:Images/down_button.png");
    Image left_image = new Image("file:Images/left_button.png");
    Image right_image = new Image("file:Images/right_button.png");
    Image menu_image = new Image("file:Images/menuimg.png");
    Image metro_image = new Image("file:Images/metrolabel.png");
    Image tram_image = new Image("file:Images/tramlabel.png");
    Image bus_image = new Image("file:Images/buslabel.png");
    Image looptijd_image = new Image("file:Images/looptijd.png");
    Image loopafstand_image = new Image("file:Images/loopafstand.png");  
    Image P_image = new Image("file:Images/P-location_image.png");  
    Image M_image = new Image("file:Images/M-location_image.png"); 
    Image T_image = new Image("file:Images/T-location_image.png"); 
    Image B_image = new Image("file:Images/B-location_image.png"); 
    List<Garage> list_garages = new ArrayList();
    public static void main(String[] args) {
   
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Park n Travel");        
        Group root = new Group();
        Scene theScene = new Scene( root );
   
        Canvas canvas = new Canvas( canvas_y, canvas_x );

        GraphicsContext gc = canvas.getGraphicsContext2D();
     
        // images start draw
        gc.drawImage( background, map_x, map_y ); // coordinaten van background zijn variable ivm navigatie
        // All deze items zijn slechts images
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        // All deze items worden objecten van hun class type (vesleepbaar en nieuwe locatie geeft een return aan de queries
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );
        

        // maak button
        Button up = new Button();
        // plaats button
        up.setGraphic(new ImageView(up_image));
        up.setLayoutX((canvas_y/2)-20);
        up.setLayoutY(5);
        up.setShape(new Circle());
        // actie on click
        up.setOnAction(e ->{ 
        if (map_y+100 <= 0){map_y = map_y + 100;}
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );
        
 
      });

        // maak button
        Button down = new Button();
        // plaats button
        down.setGraphic(new ImageView(down_image));
        down.setLayoutX((canvas_y/2)-20);
        down.setLayoutY((canvas_x-75));
        down.setShape(new Circle());
        // actie on click
        down.setOnAction(e->{        
        if (map_y-100 >= -9400){map_y = map_y - 100;}
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );  
        

    });
        // maak button
        Button left = new Button();
        // plaats button plaatje ect
        left.setGraphic(new ImageView(left_image));
        left.setLayoutX(5);
        left.setLayoutY((canvas_x/2)-20);
        left.setShape(new Circle());
        // actie on click
        left.setOnAction(e-> {       
        if (map_x+150 <= 0){map_x = map_x + 150;}
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );  
        

      });
        // maak button
        Button right = new Button();
        // plaats button plaatje ect
        right.setGraphic(new ImageView(right_image));
        right.setLayoutX(canvas_y-82);
        right.setLayoutY((canvas_x/2)-20);
        right.setShape(new Circle());
        // actie on click
        right.setOnAction(e -> {       
        if ((map_x)-150 >= -9400){map_x = map_x - 150;}
        gc.drawImage( background, map_x, map_y );        
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );
        for (Garage garage : list_garages){
            garage.setPositionX(map_x);
            garage.setPositionY(map_y);
            garage.Draw(gc);

                }

    });   
        

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
        for (Garage garage : list_garages){
            garage.setPositionX(map_x);
            garage.setPositionY(map_y);
            garage.Draw(gc);

                }
        // add buttons om te tekenen
        root.getChildren().addAll(canvas,right,left,up,down);
        primaryStage.setScene( theScene );

        primaryStage.show();
        // images end draw
        


      
}
    
}
