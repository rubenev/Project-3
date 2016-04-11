
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
import javafx.stage.Stage;


public class Main extends Application{
    
    
    int map_x = -4000;
    int map_y = -5700;
    int canvas_x = 800;
    int canvas_y = 1200;
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
        
        // Alle images worden later op andere manier getekend
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
      });

        // maak button
        Button down = new Button();
        // plaats button
        down.setGraphic(new ImageView(down_image));
        down.setLayoutX((canvas_y/2)-20);
        down.setLayoutY((canvas_x-75));
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
    });
        // maak button
        Button left = new Button();
        // plaats button plaatje ect
        left.setGraphic(new ImageView(left_image));
        left.setLayoutX(5);
        left.setLayoutY((canvas_x/2)-20);
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
      });
        // maak button
        Button right = new Button();
        // plaats button plaatje ect
        right.setGraphic(new ImageView(right_image));
        right.setLayoutX(canvas_y-82);
        right.setLayoutY((canvas_x/2)-20);
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
    });     
        // add buttons om te tekenen
        root.getChildren().addAll(canvas,right,left,up,down);
        primaryStage.setScene( theScene );
        primaryStage.show();
        // images end draw
        


      
}
    
}
