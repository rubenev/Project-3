
package Main;

// Commit test Selim Esengin 11:20

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application{
    
    Button up_button;
    Button down_button;
    Button left_button;
    Button right_button;
    int map_x = -4000;
    int map_y = -5700;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Park n Travel");        
        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );

        up_button = new Button();
        up_button.setText("Up");
        
        up_button.setOnAction(e->{
            map_x = map_x + 100;
        });
        
        Canvas canvas = new Canvas( 1200, 800 );
        
       
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image background = new Image("file:Images/mapRotterdam.png");
        Image up_image = new Image("file:Images/up_button.png");
        if(background!=null){     
        gc.drawImage( background, map_x, map_y );
        gc.drawImage( up_image, 550, 10 );}
        root.getChildren().addAll( up_button,canvas );
        

  

        primaryStage.show();

      
}
}
