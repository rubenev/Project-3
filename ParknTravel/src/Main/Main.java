
package Main;

// Commit test Selim Esengin 11:20



import Main.Stations.Metro;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {
    
    double map_x = -3500;
    double map_y = -5400;
    double canvas_x = 1000;
    double canvas_y = 1500;
    
   //---------------------------------------------------------//
   //              berekenen van null punt x en y             //
   //---------------------------------------------------------//    
    double latRad = 51.988431*Math.PI/180;
    double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
    double null_ylat = (10159/2)-(10563*mercN/(2*Math.PI));
    double null_xlong = (4.396787 + 180.0) * (10563 / 360);  

    Image logo = new Image("Images/Halfvol.png");
    Image background = new Image("Images/mapRotterdam.png");
    Image up_image = new Image("Images/up_button.png");
    Image down_image = new Image("Images/down_button.png");
    Image left_image = new Image("Images/left_button.png");
    Image right_image = new Image("Images/right_button.png");
    Image menu_image = new Image("Images/menuimg.png");
    Image looptijd_image = new Image("Images/looptijd.png");
    Image loopafstand_image = new Image("Images/loopafstand.png");
    
    Image B_image_info = new Image("Images/B-location_image_info.png"); //TEWST
    
    Menu menu = new Menu();
    CheckBox bus = new CheckBox();
    CheckBox tram = new CheckBox();
    CheckBox metro = new CheckBox();
    ImageView images = new ImageView();
    
    List<Garage> list_garages = new ArrayList();
    List<Metro> list_metro = new ArrayList();
    public static void main(String[] args) {
   
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException{

        primaryStage.setTitle("Park n Travel");        
        Group root = new Group();
        Scene theScene = new Scene( root );
        // data is database
        Database data = new Database();
        List<Garage> new_list= data.getGaragelist(); //list van garages uit database
        for (Garage garage : new_list){
            garage.setPositionY(map_y); //past alle Y van de garages aan zodat het klopt met de map
            garage.setPositionX(map_x); //past alle Y van de garages aan zodat het klopt met de map
            }
        List<Metro> metro_list= data.getMetroList(); //list van garages uit database
        for (Metro metro : metro_list){
            metro.setPositionY(map_y); //past alle Y van de garages aan zodat het klopt met de map
            metro.setPositionX(map_x); //past alle Y van de garages aan zodat het klopt met de map
            }
        images.setImage(B_image_info);
        images.setOnMouseClicked(e ->{
                      System.out.println("Tile pressed ");
                      
                  
             });
        
        Canvas canvas = new Canvas( canvas_y, canvas_x );
        
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Show All");
        for (Garage garage : new_list){
            comboBox.getItems().add(garage.getName());
        }
        comboBox.setValue("Select Garage");       
        comboBox.setBackground(Background.EMPTY);
        comboBox.setLayoutX(20);
        comboBox.setLayoutY(20);
        
        comboBox.setOnAction(e -> {
          comboBox.getValue();  
          for (Garage garage : new_list){
              if (comboBox.getValue() == garage.getName()){
                double temp_mapx = map_x*1; // we bewaren hier een oude map_x en y onder andere naam
                double temp_mapy = map_y*1;
                map_x = (temp_mapx - garage.getPositionX())+(canvas_y/2); // 2000 = -1800
                map_y = (temp_mapy - garage.getPositionY())+(canvas_x/2); // 4000 = 4000 - 1600 + 1000
                for (Garage garage2 : new_list){
                    garage2.setPositionY(map_y-temp_mapy); //hier vullen we het verschil van de som in als nieuwe locatiemeter van de garages
                    garage2.setPositionX(map_x-temp_mapx); //past alle Y van de garages aan zodat het klopt met de map
                    }
                for (Metro metro : metro_list){
                    metro.setPositionY(map_y-temp_mapy); //past alle Y van de garages aan zodat het klopt met de map
                    metro.setPositionX(map_x-temp_mapx); //past alle Y van de garages aan zodat het klopt met de map
                    
                System.out.println(metro.getPositionX() + " " + metro.getPositionY());}}
          }
          System.out.println(comboBox.getValue());
        });
        
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // maak button
        Button up = new Button();
        // plaats button
        up.setGraphic(new ImageView(up_image));
        up.setLayoutX((canvas_y/2)-20);
        up.setLayoutY(5);
        up.setShape(new Circle());
        // actie on click
        up.setOnAction(e ->{ 
        if (map_y+100 <= 0){map_y = map_y + 100;
        for (Garage garage : new_list){
            garage.setPositionY(+ 100);}
        for (Metro metro : metro_list){
            metro.setPositionY(+ 100);
        }}}); //past alle Y van de garages aan UPDATE      
        // maak button
        Button down = new Button();
        // plaats button
        down.setGraphic(new ImageView(down_image));
        down.setLayoutX((canvas_y/2)-20);
        down.setLayoutY((canvas_x-75));
        down.setShape(new Circle());
        // actie on click
        down.setOnAction(e->{        
        if (map_y-100 >= -9400){map_y = map_y - 100;
        for (Garage garage : new_list){
            garage.setPositionY(- 100);}
        for (Metro metro : metro_list){
            metro.setPositionY(- 100);
        }}}); //past alle Y van de garages aan UPDATE    
        // maak button
        Button left = new Button();
        // plaats button plaatje ect
        left.setGraphic(new ImageView(left_image));
        left.setLayoutX(5);
        left.setLayoutY((canvas_x/2)-20);
        left.setShape(new Circle());
        // actie on click
        left.setOnAction(e-> {       
        if (map_x+150 <= 0){map_x = map_x + 150;
        for (Garage garage : new_list){
            garage.setPositionX(+ 150);}
        for (Metro metro : metro_list){
            metro.setPositionX(+ 150);
        }}}); //past alle X van de garages aan UPDATE    
        // maak button
        Button right = new Button();
        // plaats button plaatje ect
        right.setGraphic(new ImageView(right_image));
        right.setLayoutX(canvas_y-82);
        right.setLayoutY((canvas_x/2)-20);
        right.setShape(new Circle());
        // actie on click
        right.setOnAction(e -> {       
        if ((map_x)-150 >= -9400){map_x = map_x - 150;
        for (Garage garage : new_list){
            garage.setPositionX(- 150);}
        for (Metro metro : metro_list){
            metro.setPositionX(- 150);
        }}});  //past alle X van de garages aan UPDATE
    
        root.getChildren().addAll(canvas,right,left,up,down,comboBox,bus,tram,metro);
        primaryStage.setScene( theScene );
        primaryStage.show();
        
        // Refresh het scherm
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime){
                //clear the canvas before painting over it
                
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                gc.drawImage( background, map_x, map_y );
                menu.InteractionCheckbox(bus, metro, tram, primaryStage, theScene);
                for (Metro metroo : metro_list){
                    if(metro.isSelected())
                    metroo.Draw(gc);
                }
                for (Garage garage : new_list){
                    if(comboBox.getValue() == garage.getName() || comboBox.getValue() == "Select Garage" || comboBox.getValue() == "Show All")
                        garage.Draw(gc);}
                
                gc.drawImage( menu_image, 5, 0 );
                gc.drawImage( B_image_info, 300, 300 );
                gc.drawImage( loopafstand_image, 70, 260 );
                gc.drawImage( looptijd_image, 130, 330 );   
                
            }
        }.start();   
    }    
}
