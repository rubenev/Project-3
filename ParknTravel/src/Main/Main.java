
package Main;

// Commit test Selim Esengin 11:20

import java.awt.event.MouseListener;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Main extends Application{
    
    JButton testknop = new JButton();
    
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
        primaryStage.setScene( theScene );
        Canvas canvas = new Canvas( canvas_y, canvas_x );
        
       testknop.setIcon(new ImageIcon("file:Images/down_button.png"));
       
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Alle images worden later op andere manier getekend
        Image background = new Image("file:Images/mapRotterdam.png");
        Image up_image = new Image("file:Images/up_buttont.png");
        Image down_image = new Image("file:Images/down_buttont.png");
        Image left_image = new Image("file:Images/left_buttont.png");
        Image right_image = new Image("file:Images/right_buttont.png");
        Image menu_image = new Image("file:Images/menuimg.png");
        Image metro_image = new Image("file:Images/metrolabel.png");
        Image tram_image = new Image("file:Images/tramlabel.png");
        Image bus_image = new Image("file:Images/buslabel.png");
        Image looptijd_image = new Image("file:Images/looptijd.png");
        Image loopafstand_image = new Image("file:Images/loopafstand.png");
        root.getChildren().addAll(canvas ); // maakt het frame
        
        primaryStage.show();   // showed het frame     
        
        // images start draw
        gc.drawImage( background, map_x, map_y ); // coordinaten van background zijn variable ivm navigatie
        // all deze images zijn knoppen. maar dat moet dus nog op een manier waar gemaakt worden
        gc.drawImage( up_image, ((canvas_y/2)-20), 10 );
        gc.drawImage( down_image, ((canvas_y/2)-20), (canvas_x-70) );
        gc.drawImage( left_image, 10, ((canvas_x/2)-20) );
        gc.drawImage( right_image, (canvas_y-70), ((canvas_x/2)-20) );
        // All deze items zijn slechts images
        gc.drawImage( menu_image, 5, 0 );
        gc.drawImage( metro_image, 10, 80 );
        gc.drawImage( tram_image, 10, 140 );
        gc.drawImage( bus_image, 10, 200 );

        // All deze items worden objecten van hun class type (vesleepbaar en nieuwe locatie geeft een return aan de queries
        gc.drawImage( loopafstand_image, 70, 260 );
        gc.drawImage( looptijd_image, 130, 330 );

        
        
        // images end draw
        


      
}
    
}
