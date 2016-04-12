
package Main;

// Commit test Selim Esengin 11:20

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
    
    int map_x = 0;
    int map_y = 0;
    int canvas_x = 1000;
    int canvas_y = 1500;
    
   //---------------------------------------------------------//
   //              berekenen van null punt x en y             //
   //---------------------------------------------------------//    
    double latRad = 51.988431*Math.PI/180;
    double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
    double null_ylat = (10159/2)-(10563*mercN/(2*Math.PI));
    double null_xlong = (4.396787 + 180.0) * (10563 / 360);  
   //---------------------------------------------------------//
   //              berekenen van haltes punt x en y           //
   //---------------------------------------------------------//   
    double latRad1 = 51.985299*Math.PI/180;
    double mercN1 = Math.log(Math.tan((Math.PI/4)+(latRad1/2)));
    double straat_ylat = (10159/2)-(10563*mercN1/(2*Math.PI));
    double straat_xlong = (4.420903 + 180.0) * (10563 / 360);  
    
    double latRad2 = 51.979266*Math.PI/180;
    double mercN2 = Math.log(Math.tan((Math.PI/4)+(latRad2/2)));
    double straat2_ylat = (10159/2)-(10563*mercN2/(2*Math.PI));
    double straat2_xlong = (4.404522 + 180.0) * (10563 / 360);  

    double latRad4 = 51.917372*Math.PI/180;
    double mercN4 = Math.log(Math.tan((Math.PI/4)+(latRad4/2)));
    double test_ylat = (10159/2)-(10563*mercN4/(2*Math.PI));
    double test_xlong = (4.485112 + 180.0) * (10563 / 360);      

    double latRad3 = 51.918209*Math.PI/180;
    double mercN3 = Math.log(Math.tan((Math.PI/4)+(latRad3/2)));
    double beurs_ylat = (10159/2)-(10563*mercN3/(2*Math.PI));
    double beurs_xlong = (4.481266 + 180.0) * (10563 / 360);  
   //---------------------------------------------------------//
   //         berekenen van lokaties van haltes op map        //
   //---------------------------------------------------------//       
    double pointystraat = ((straat_ylat - null_ylat)*1781);
    double pointxstraat = ((straat_xlong - null_xlong)*1758);
    double pointystraat2 = ((straat2_ylat - null_ylat)*1781);
    double pointxstraat2 = ((straat2_xlong - null_xlong)*1758);
    double pointytest = ((test_ylat - null_ylat)*1781);
    double pointxtest = ((test_xlong - null_xlong)*1758);
    double pointybeurs = ((beurs_ylat - null_ylat)*1781);
    double pointxbeurs = ((beurs_xlong - null_xlong)*1758);    

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
    Image dot = new Image("file:Images/dot.png");  
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
        map_y = map_y + 100;
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );
                gc.drawImage( dot, pointxtest+map_x, pointytest+map_y );     
       gc.drawImage( dot, pointxstraat+map_x, pointystraat+map_y );   
       gc.drawImage( dot, pointxstraat2+map_x, pointystraat2+map_y );  
       gc.drawImage( dot, pointxbeurs+map_x, pointybeurs+map_y );  
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
        map_y = map_y - 100;
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );  
        
        // Test punten op map //
       gc.drawImage( dot, pointxtest+map_x, pointytest+map_y );     
       gc.drawImage( dot, pointxstraat+map_x, pointystraat+map_y );   
       gc.drawImage( dot, pointxstraat2+map_x, pointystraat2+map_y );  
       gc.drawImage( dot, pointxbeurs+map_x, pointybeurs+map_y );  
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
        map_x = map_x + 150;
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );  
        
        // Test punten op map //
       gc.drawImage( dot, pointxtest+map_x, pointytest+map_y );     
       gc.drawImage( dot, pointxstraat+map_x, pointystraat+map_y );   
       gc.drawImage( dot, pointxstraat2+map_x, pointystraat2+map_y );  
       gc.drawImage( dot, pointxbeurs+map_x, pointybeurs+map_y );  
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
        map_x = map_x - 150;
        gc.drawImage( background, map_x, map_y );        
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );
        
        // Test punten op map //
       gc.drawImage( dot, pointxtest+map_x, pointytest+map_y );     
       gc.drawImage( dot, pointxstraat+map_x, pointystraat+map_y );   
       gc.drawImage( dot, pointxstraat2+map_x, pointystraat2+map_y );  
       gc.drawImage( dot, pointxbeurs+map_x, pointybeurs+map_y );  
    });   


//        gc.drawImage( dot, 1245, 280 ); 
//        gc.drawImage( dot, pointxnieuw, pointynieuw );   
        
        
//        gc.drawImage( dot, bus_x, bus_y );
        // add buttons om te tekenen
        root.getChildren().addAll(canvas,right,left,up,down);
        primaryStage.setScene( theScene );
        

        System.out.println("x = " + pointystraat + ", " + "y = "+ pointxstraat);
//        System.out.println("x = " + pointxstraat + ", " + "y = "+ pointystraat);
        primaryStage.show();
        // images end draw
        


      
}
    
}
