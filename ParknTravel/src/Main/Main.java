
package Main;

// Commit test Selim Esengin 11:20

import Main.Classes.Images;
import Main.Stations.Tram;
import Main.Stations.Bus;
import Main.Stations.Metro;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import static javafx.scene.paint.Color.RED;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class Main extends Application {
    
    double map_x = -3500;
    double map_y = -5400;
    double canvas_x = 1000;
    double canvas_y = 1500;
    double selecten_garageX;
    double selecten_garageY;
    
   //---------------------------------------------------------//
   //              berekenen van null punt x en y             //
   //---------------------------------------------------------//    
    double latRad = 51.988431*Math.PI/180;
    double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
    double null_ylat = (10159/2)-(10563*mercN/(2*Math.PI));
    double null_xlong = (4.396787 + 180.0) * (10563 / 360);      
    Button exitButton = new Button();
    Menu menu = new Menu();
    CheckBox bus = new CheckBox();
    CheckBox tram = new CheckBox();
    CheckBox metro = new CheckBox();
    ImageView images = new ImageView();
    Label looptijdtext = new Label();
    Label loopafstandtext = new Label();
    
    List<Garage> list_garages = new ArrayList();
    List<Metro> list_metro = new ArrayList();
    List<Tram> list_tram = new ArrayList();
    List<Button> GarageButtonList = new ArrayList();
    List<Label> GarageLabelList = new ArrayList();
    List<Button> MetroButtonList = new ArrayList();
    List<Label> MetroLabelList = new ArrayList();
    public static void main(String[] args) {
   
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException{
        primaryStage.setFullScreen(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        canvas_y = screenSize.getWidth();
        canvas_x = screenSize.getHeight();


        System.out.println(primaryStage.getHeight());
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
        List<Tram> tram_list = data.getTramList();
        for (Tram tram : tram_list){
            tram.setPositionY(map_y);
            tram.setPositionX(map_x);
        }
        List<Bus> bus_list = data.getBusList();
        for (Bus bus: bus_list){
            bus.setPositionY(map_y);
            bus.setPositionX(map_x);
        }
        ///////////////////////////////////////////////////////////////
        //                           SLIDER                         ///
        ///////////////////////////////////////////////////////////////
        Slider slider_loopafstand = new Slider(); //loopafstand
        slider_loopafstand.setMin(0);
        slider_loopafstand.setMax(1000);// 80/100
        slider_loopafstand.setValue(400); // 1,25 40pixels is 50 meter
        slider_loopafstand.setLayoutX(20);
        slider_loopafstand.setLayoutY(265);
        slider_loopafstand.setShowTickLabels(false);
        slider_loopafstand.setShowTickMarks(false);
        slider_loopafstand.setMajorTickUnit(50);
        slider_loopafstand.setMinorTickCount(50);
        slider_loopafstand.setBlockIncrement(10);
        slider_loopafstand.setCursor(Cursor.HAND);
        slider_loopafstand.setMinSize(240, 1);
        slider_loopafstand.setBackground(Background.EMPTY);
        slider_loopafstand.getStylesheets().add(getClass().getResource("sliderstylesheet.css").toExternalForm()); // verandert de layout van de slider met behulp van css 
        
        Slider slider_looptijd = new Slider(); // looptijd
        slider_looptijd.setMin(0);
        slider_looptijd.setMax(1000);
        slider_looptijd.setValue(400); // 66 per minuut
        slider_looptijd.setLayoutX(20);
        slider_looptijd.setLayoutY(325);
        slider_looptijd.setShowTickLabels(false);
        slider_looptijd.setShowTickMarks(false);
        slider_looptijd.setMajorTickUnit(50);
        slider_looptijd.setMinorTickCount(50);
        slider_looptijd.setBlockIncrement(10);
        slider_looptijd.setCursor(Cursor.HAND);
        slider_looptijd.setMinSize(240, 1);
        slider_looptijd.setBackground(Background.EMPTY);
        slider_looptijd.getStylesheets().add(getClass().getResource("sliderstylesheet2.css").toExternalForm()); // verandert de layout van de slider met behulp van css

        
        
        Canvas canvas = new Canvas(canvas_y,canvas_x);

        ///////////////////////////////////////////////////////////////
        //                           COMBOBOX                       ///
        ///////////////////////////////////////////////////////////////
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Show All");
        for (Garage garage : new_list){comboBox.getItems().add(garage.getName());}
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
                    garage2.setPositionX(map_x-temp_mapx);} //past alle Y van de garages aan zodat het klopt met de map       
                for (Metro metro : metro_list){
                    metro.setPositionY(map_y-temp_mapy); //past alle Y van de garages aan zodat het klopt met de map
                    metro.setPositionX(map_x-temp_mapx);} //past alle Y van de garages aan zodat het klopt met de map            
                for (Tram tram : tram_list){
                    tram.setPositionY(map_y-temp_mapy);
                    tram.setPositionX(map_x-temp_mapx);}
                for (Bus bus : bus_list){
                    bus.setPositionY(map_y-temp_mapy);
                    bus.setPositionX(map_x-temp_mapx);
                }}}});
        
        GraphicsContext gc = canvas.getGraphicsContext2D();  
        ///////////////////////////////////////////////////////////////
        //                     NAVIGATIE KNOPPEN                    ///
        ///////////////////////////////////////////////////////////////
        Button up = new Button();
        // plaats button
        up.setGraphic(new ImageView(Images.up_image));
        up.setLayoutX((canvas_y/2)-20);
        up.setLayoutY(5);
        up.setShape(new Circle());
        // actie on click
        up.setOnAction(e ->{ 
        if (map_y+100 <= 0){map_y = map_y + 100;
        for (Garage garage : new_list){
            garage.setPositionY(+ 100);}
        for (Metro metro : metro_list){
            metro.setPositionY(+ 100);}
        for (Tram tram : tram_list){
            tram.setPositionY(+ 100);}
        for (Bus bus : bus_list){
            bus.setPositionY(+ 100);
        }
        }}); //past alle Y van de garages aan UPDATE      
        // maak button
        Button down = new Button();
        // plaats button
        down.setGraphic(new ImageView(Images.down_image));
        down.setLayoutX((canvas_y/2)-20);
        down.setLayoutY((canvas_x-75));
        down.setShape(new Circle());
        // actie on click
        down.setOnAction(e->{        
        if (map_y-100 >= -9400){map_y = map_y - 100;
        for (Garage garage : new_list){
            garage.setPositionY(- 100);}
        for (Metro metro : metro_list){
            metro.setPositionY(- 100);}
        for (Tram tram : tram_list){
            tram.setPositionY(- 100);}
        for (Bus bus : bus_list){
            bus.setPositionY(- 100);}}}); //past alle Y van de garages aan UPDATE    
        // maak button
        Button left = new Button();
        // plaats button plaatje ect
        left.setGraphic(new ImageView(Images.left_image));
        left.setLayoutX(5);
        left.setLayoutY((canvas_x/2)-20);
        left.setShape(new Circle());
        // actie on click
        left.setOnAction(e-> {       
        if (map_x+150 <= 0){map_x = map_x + 150;
        for (Garage garage : new_list){
            garage.setPositionX(+ 150);}
        for (Metro metro : metro_list){
            metro.setPositionX(+ 150);}
        for (Tram tram : tram_list){
            tram.setPositionX(+ 150);}
        for (Bus bus : bus_list){
            bus.setPositionX(+ 150);}}}); //past alle X van de garages aan UPDATE    
        // maak button
        Button right = new Button();
        // plaats button plaatje ect
        right.setGraphic(new ImageView(Images.right_image));
        right.setLayoutX(canvas_y-82);
        right.setLayoutY((canvas_x/2)-20);
        right.setShape(new Circle());
        // actie on click
        right.setOnAction(e -> {       
        if ((map_x)-150 >= -9400){map_x = map_x - 150;
        for (Garage garage : new_list){
            garage.setPositionX(- 150);}
        for (Metro metro : metro_list){
            metro.setPositionX(- 150);}
        for (Tram tram : tram_list){
            tram.setPositionX(- 150);}
        for (Bus bus : bus_list){
            bus.setPositionX(-150);}}});  //past alle X van de garages aan UPDATE
        
        ///////////////////////////////////////////////////////////////
        //                Button n Label bij elke garage             //
        ///////////////////////////////////////////////////////////////
         for (Garage garage : new_list){
              Button garagebutt = new Button();
              Label garagelabel = new Label();
          garagebutt.setOnMouseClicked(e -> {
              System.out.println(garage.getName());
              if (garage.isClicked == false){
                  garage.setImg(Images.B_image_info);
                  garagelabel.setVisible(true);
              garage.isClicked = true;}
            else{garage.setImg(new Image("Images/P-location_image.png"));garagelabel.setVisible(false);garage.isClicked = false;}primaryStage.setScene( theScene );});
          garagebutt.setLayoutX(garage.Position_x);
          garagebutt.setLayoutY(garage.Position_y); 
          garagebutt.setCursor(Cursor.OPEN_HAND);
          
          garagelabel.setLayoutX(garage.Position_x + 80);
          garagelabel.setLayoutY(garage.Position_y + 30);
          garagelabel.setText(garage.getName() + "\n" + garage.getDescription());
          garagelabel.setVisible(false);
          
          GarageLabelList.add(garagelabel);
          GarageButtonList.add(garagebutt);}
         
         for (Metro metro : metro_list){
              Button garagebutt = new Button();
              Label garagelabel = new Label();
          garagebutt.setOnMouseClicked(e -> {
              if (metro.isClicked == false){
                  garagelabel.setVisible(true);
              metro.isClicked = true;}
            else{garagelabel.setVisible(false);metro.isClicked = false;}});
          garagebutt.setLayoutX(metro.getPositionX());
          garagebutt.setLayoutY(metro.getPositionY()); 
          garagebutt.setCursor(Cursor.OPEN_HAND);
          
          garagelabel.setLayoutX(metro.getPositionX() + 80);
          garagelabel.setLayoutY(metro.getPositionY() + 30);
          garagelabel.setText(metro.getName() + "\n" + metro.getDescription());
          garagelabel.setVisible(false);
          
          MetroLabelList.add(garagelabel);
          MetroButtonList.add(garagebutt);}
        ////////////////////////////////////////////////////////////////
        //                         Eixt full screen                   //
         /////////////////////////////////////////////////////////////
         
         exitButton.setLayoutX(100);
         exitButton.setLayoutY(100);
         exitButton.setOnAction(e -> { primaryStage.setFullScreen(false);
            primaryStage.setWidth(1500);
            primaryStage.setHeight(1000);
              canvas_y = 1500;
              canvas_x = 1000;
        right.setLayoutX(canvas_y-102);
        right.setLayoutY((canvas_x/2)-20);
        down.setLayoutX((canvas_y/2)-20);
        down.setLayoutY((canvas_x-115));    
        left.setLayoutY((canvas_x/2)-20);
         up.setLayoutX((canvas_y/2)-20);       
         });
        
        
        ///////////////////////////////////////////////////////////////
        //                         ROOT CREeREN                     ///
        ///////////////////////////////////////////////////////////////

        loopafstandtext.setLayoutX(190);
        loopafstandtext.setLayoutY(250);
        loopafstandtext.setTextFill(Color.web("#FFFFFF"));
        looptijdtext.setLayoutX(190);
        looptijdtext.setLayoutY(310);       
        looptijdtext.setTextFill(Color.web("#FFFFFF"));
//        canvas.setLayoutX(canvas_x);
//        canvas.setLayoutY(canvas_y);       
        root.getChildren().addAll(canvas,right,left,up,down,comboBox,bus,tram,metro,slider_loopafstand,slider_looptijd,loopafstandtext,looptijdtext,exitButton);
        root.getChildren().addAll(GarageButtonList);
        root.getChildren().addAll(GarageLabelList);
        root.getChildren().addAll(MetroLabelList);
        root.getChildren().addAll(MetroButtonList);

        primaryStage.setScene( theScene );
        primaryStage.show();
        
        // Refresh het scherm
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime){
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());        
                gc.drawImage( Images.background, map_x, map_y );
                menu.InteractionCheckbox(bus, metro, tram, primaryStage, theScene, MetroButtonList);
                // text loopafstand en tijd
                double newloopafstanddouble = (slider_loopafstand.getValue() * 1.25);
                int newloopafstandint = (int)newloopafstanddouble;
                String newloopafstandtext = Integer.toString(newloopafstandint);
                loopafstandtext.setText(newloopafstandtext + " meter");  
                double newlooptijddouble = (slider_looptijd.getValue() / 66);
                int newlooptijdint = (int)newlooptijddouble;
                String newlooptijdtext = Integer.toString(newlooptijdint);
                looptijdtext.setText(newlooptijdtext + " minuten");   // 5 km/h // 83m per minuut // 66 pixels per minuut
                               
                for (Metro metroo : metro_list){
                    if(metro.isSelected())
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - metroo.getPositionY()),2) + Math.pow(((menu.getgarX())- metroo.getPositionX()),2))) <= slider_loopafstand.getValue())
                            &&((Math.sqrt(Math.pow(((menu.getgarY()) - metroo.getPositionY()),2) + Math.pow(((menu.getgarX())- metroo.getPositionX()),2))) <= slider_looptijd.getValue()))
                        {
                            metroo.Draw(gc);}
                        else{gc.drawImage( Images.metro_imagebw, metroo.getPositionX(), metroo.getPositionY() );}
                }
                for (Tram tramm : tram_list){
                    if(tram.isSelected()){   
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - tramm.getPositionY()),2) + Math.pow(((menu.getgarX())- tramm.getPositionX()),2))) <= slider_loopafstand.getValue())
                           &&((Math.sqrt(Math.pow(((menu.getgarY()) - tramm.getPositionY()),2) + Math.pow(((menu.getgarX())- tramm.getPositionX()),2))) <= slider_looptijd.getValue()))
                        {
                            
                            tramm.Draw(gc);}
                        else{gc.drawImage( Images.tram_imagebw, tramm.getPositionX(), tramm.getPositionY() );}
                }
                }
                for (Bus buss : bus_list){
                    if (bus.isSelected()){   
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - buss.getPositionY()),2) + Math.pow(((menu.getgarX())- buss.getPositionX()),2))) <= slider_loopafstand.getValue())
                           && (Math.sqrt(Math.pow(((menu.getgarY()) - buss.getPositionY()),2) + Math.pow(((menu.getgarX())- buss.getPositionX()),2))) <= slider_looptijd.getValue())
                        {
                            buss.Draw(gc);}
                        else{gc.drawImage( Images.bus_imagebw, buss.getPositionX(), buss.getPositionY() );}
                }}
                for (Garage garage : new_list){
                    if(comboBox.getValue() == garage.getName() || comboBox.getValue() == "Select Garage" || comboBox.getValue() == "Show All"){
                          
                        garage.Draw(gc);}
                        if(comboBox.getValue() == garage.getName()){
                            
                            menu.setgarY(garage.getPositionY());
                            menu.setgarX(garage.getPositionX());
                        }
}               
                
                gc.drawImage( Images.menu_image, 5, 0 );
               // menu.paint(comboBox,55, 55, 55);
            } 
        }.start();   
    } 
    
}


/*
        N = slider
        i = y  j = x

        r = math.sqrt((n/2 - x)**2 + (n/2 - y)**2)

        if math.sqrt((300/2 - (selecten_garageX-buss.getPositionX()))**2 + (300/2 - (selecten_garageY-buss.getPositionY()))**2) <= 300 

*/