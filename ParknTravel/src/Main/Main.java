
package Main;


import Main.Classes.Images;
import Main.Stations.Tram;
import Main.Stations.Bus;
import Main.Stations.Metro;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Toolkit;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


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
    boolean help_open = false;
    Menu menu = new Menu(); 
    CheckBox bus = new CheckBox();
    CheckBox tram = new CheckBox();
    CheckBox metro = new CheckBox();
    ImageView images = new ImageView();
    ImageView menu_image = new ImageView(Images.menu_image);

    List<Garage> list_garages = new ArrayList();
    List<Metro> list_metro = new ArrayList();
    List<Tram> list_tram = new ArrayList();
    List<Button> GarageButtonList = new ArrayList();
    List<Label> GarageLabelList = new ArrayList();
    List<Button> MetroButtonList = new ArrayList();
    List<Label> MetroLabelList = new ArrayList();
    List<Button> BusButtonList = new ArrayList();
    List<Label> BusLabelList = new ArrayList();
    List<Button> TramButtonList = new ArrayList();
    List<Label> TramLabelList = new ArrayList();
    List<Button> AllButtons = new ArrayList();
    List<Label> AllLabels = new ArrayList();
      
    public static void main(String[] args) {        launch(args);       }

    @Override
    public void start(Stage primaryStage) throws InterruptedException{
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Park & Travel");   
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        canvas_y = screenSize.getWidth();
        canvas_x = screenSize.getHeight();
        menu.setgarY(canvas_x/2);
        menu.setgarX(canvas_y/2);
        Canvas canvas = new Canvas(canvas_y,canvas_x); // creeeren van beeld enzo
        GraphicsContext gc = canvas.getGraphicsContext2D();  
        Group root = new Group();
        Scene theScene = new Scene( root );
        
        Database data = new Database(); // data is database
        List<Garage> new_list= data.getGaragelist(); //list van garages uit database
        for (Garage garage : new_list){
            garage.setPositionY(map_y); //past alle Y van de garages aan zodat het klopt met de map
            garage.setPositionX(map_x);} //past alle Y van de garages aan zodat het klopt met de map
        List<Metro> metro_list= data.getMetroList(); //list van garages uit database
        for (Metro metro : metro_list){
            metro.setPositionY(map_y); //past alle Y van de garages aan zodat het klopt met de map
            metro.setPositionX(map_x); //past alle Y van de garages aan zodat het klopt met de map
            }
        List<Tram> tram_list = data.getTramList();
        for (Tram tram : tram_list){
            tram.setPositionY(map_y);
            tram.setPositionX(map_x);}
        List<Bus> bus_list = data.getBusList();
        for (Bus bus: bus_list){
            bus.setPositionY(map_y);
            bus.setPositionX(map_x);}
        ///////////////////////////////////////////////////////////////
        //                           SLIDER                         ///
        ///////////////////////////////////////////////////////////////
        Slider slider_loopafstand = new Slider(); //loopafstand // 1,25 40pixels is 50 meter
        slider_loopafstand.setMin(0); slider_loopafstand.setMax(1000); slider_loopafstand.setValue(400); slider_loopafstand.setLayoutX(20); slider_loopafstand.setLayoutY(265);
        slider_loopafstand.setShowTickLabels(false); slider_loopafstand.setShowTickMarks(false); slider_loopafstand.setMajorTickUnit(50); slider_loopafstand.setMinorTickCount(50);
        slider_loopafstand.setBlockIncrement(10); slider_loopafstand.setCursor(Cursor.HAND); slider_loopafstand.setMinSize(240, 1); slider_loopafstand.setBackground(Background.EMPTY);
        slider_loopafstand.getStylesheets().add(getClass().getResource("sliderstylesheet.css").toExternalForm()); // verandert de layout van de slider met behulp van css 
        
        Slider slider_looptijd = new Slider(); // looptijd // 66 pixels per minuut
        slider_looptijd.setMin(0); slider_looptijd.setMax(1000); slider_looptijd.setValue(400); slider_looptijd.setLayoutX(20); slider_looptijd.setLayoutY(325);
        slider_looptijd.setShowTickLabels(false); slider_looptijd.setShowTickMarks(false); slider_looptijd.setMajorTickUnit(50); slider_looptijd.setMinorTickCount(50);
        slider_looptijd.setBlockIncrement(10); slider_looptijd.setCursor(Cursor.HAND); slider_looptijd.setMinSize(240, 1); slider_looptijd.setBackground(Background.EMPTY);
        slider_looptijd.getStylesheets().add(getClass().getResource("sliderstylesheet2.css").toExternalForm()); // verandert de layout van de slider met behulp van css
        ///////////////////////////////////////////////////////////////
        //                           COMBOBOX                       ///
        ///////////////////////////////////////////////////////////////
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Show All");
        for (Garage garage : new_list){comboBox.getItems().add(garage.getName());} // hier vullen we de comboBox aan met de garages
        comboBox.setValue("Select Garage");       
        comboBox.setBackground(Background.EMPTY);
        comboBox.setLayoutX(20);
        comboBox.setLayoutY(20);      
        comboBox.setOnAction(e -> {
          comboBox.getValue(); 
          canvas_y = primaryStage.getWidth();
          canvas_x = primaryStage.getHeight();
          for (Garage garage : new_list){
              if (comboBox.getValue() == garage.getName()){
                double temp_mapx = map_x*1; // we bewaren hier een oude map_x en y onder andere naam
                double temp_mapy = map_y*1;
                map_x = (temp_mapx - garage.getPositionX())+(canvas_y/2); // 2000 = -1800
                map_y = (temp_mapy - garage.getPositionY())+(canvas_x/2); // 4000 = 4000 - 1600 + 1000
                double moveX = map_x-temp_mapx;
                double moveY = map_y-temp_mapy;
                menu.moveButtonList(moveX, moveY,AllButtons,AllLabels,new_list,metro_list,tram_list,bus_list);}}});   
        
        ///////////////////////////////////////////////////////////////
        //                     NAVIGATIE KNOPPEN                    ///
        ///////////////////////////////////////////////////////////////
        Button up = new Button(); // maak button
        up.setGraphic(new ImageView(Images.up_image));up.setLayoutX((canvas_y/2)-20);up.setLayoutY(5);up.setShape(new Circle());       
        up.setOnAction(e ->{ // actie on click
        if (map_y+100 <= 0){map_y = map_y + 100;
        menu.moveButtonList(0, 100,AllButtons,AllLabels,new_list,metro_list,tram_list,bus_list);
        }}); //past alle Y van de garages aan UPDATE      
        
        Button down = new Button(); // maak button
        down.setGraphic(new ImageView(Images.down_image));down.setLayoutX((canvas_y/2)-20);down.setLayoutY((canvas_x-75));down.setShape(new Circle());      
        down.setOnAction(e->{   // actie on click     
        if (map_y-100 >= -9400){map_y = map_y - 100;
        menu.moveButtonList(0, - 100,AllButtons,AllLabels,new_list,metro_list,tram_list,bus_list);}}); //past alle Y van de garages aan UPDATE    
        
        Button left = new Button();// maak button
        left.setGraphic(new ImageView(Images.left_image));left.setLayoutX(5);left.setLayoutY((canvas_x/2)-20);left.setShape(new Circle());       
        left.setOnAction(e-> {      // actie on click 
        if (map_x+150 <= 0){map_x = map_x + 150;
        menu.moveButtonList(150, 0,AllButtons,AllLabels,new_list,metro_list,tram_list,bus_list);}}); //past alle X van de garages aan UPDATE    
        
        Button right = new Button(); // maak button
        right.setGraphic(new ImageView(Images.right_image));right.setLayoutX(canvas_y-82);right.setLayoutY((canvas_x/2)-20);right.setShape(new Circle());       
        right.setOnAction(e -> {       // actie on click
        if ((map_x)-150 >= -9400){map_x = map_x - 150;
        menu.moveButtonList(-150, 0,AllButtons,AllLabels,new_list,metro_list,tram_list,bus_list);}});  //past alle X van de garages aan UPDATE
        
        ///////////////////////////////////////////////////////////////
        //                Button n Label bij elke garage             //
        ///////////////////////////////////////////////////////////////
         for (Garage garage : new_list){
              Button garagebutt = new Button();
              Label garagelabel = new Label();
              garagebutt.setStyle("-fx-background-color: transparent");
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
              garagebutt.setStyle("-fx-background-color: transparent");
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
         
         for (Bus bus : bus_list){
              Button garagebutt = new Button();
              Label garagelabel = new Label();
              garagebutt.setStyle("-fx-background-color: transparent");
          garagebutt.setOnMouseClicked(e -> {
              if (bus.isClicked == false){
                  garagelabel.setVisible(true);
              bus.isClicked = true;}
            else{garagelabel.setVisible(false);bus.isClicked = false;}});
          garagebutt.setLayoutX(bus.getPositionX());
          garagebutt.setLayoutY(bus.getPositionY()); 
          garagebutt.setCursor(Cursor.OPEN_HAND);
          
          garagelabel.setLayoutX(bus.getPositionX() + 80);
          garagelabel.setLayoutY(bus.getPositionY() + 30);
          garagelabel.setText(bus.getName() + "\n" + bus.getDescription());
          garagelabel.setVisible(false);
          
          BusLabelList.add(garagelabel);
          BusButtonList.add(garagebutt);}
         
         for (Tram tram : tram_list){
              Button garagebutt = new Button();
              garagebutt.setStyle("-fx-background-color: transparent");
              Label garagelabel = new Label();
          garagebutt.setOnMouseClicked(e -> {
              if (tram.isClicked == false){
                  garagelabel.setVisible(true);
              tram.isClicked = true;}
            else{garagelabel.setVisible(false);tram.isClicked = false;}});
          garagebutt.setLayoutX(tram.getPositionX());
          garagebutt.setLayoutY(tram.getPositionY()); 
          garagebutt.setCursor(Cursor.OPEN_HAND);
          
          garagelabel.setLayoutX(tram.getPositionX() + 80);
          garagelabel.setLayoutY(tram.getPositionY() + 30);
          garagelabel.setText(tram.getName() + "\n" + tram.getDescription());
          garagelabel.setVisible(false);
          
          TramLabelList.add(garagelabel);
          TramButtonList.add(garagebutt);}


        ////////////////////////////////////////////////////////////////
        //                      Exit en enter full screen            //
         /////////////////////////////////////////////////////////////       
        exitButton exitButton = new exitButton(root,canvas_y,canvas_x,primaryStage,up,down,left,right);    
        ///////////////////////////////////////////////////////////////
        //                         ROOT CREeREN                     ///
        ///////////////////////////////////////////////////////////////
        
        AllButtons.addAll(GarageButtonList);
        AllButtons.addAll(MetroButtonList);
        AllButtons.addAll(BusButtonList);
        AllButtons.addAll(TramButtonList);
        AllLabels.addAll(GarageLabelList);
        AllLabels.addAll(MetroLabelList);
        AllLabels.addAll(BusLabelList);
        AllLabels.addAll(TramLabelList);
         menu_image.setLayoutX(5);
         menu_image.setLayoutY(0);
        Helpbutton buttonsmenu = new Helpbutton();    
        root.getChildren().addAll(canvas);
        root.getChildren().addAll(AllButtons);
        root.getChildren().addAll(AllLabels);
        root.getChildren().addAll(menu_image,bus,tram,metro,comboBox,up,buttonsmenu.gethelpscreen(),buttonsmenu.getHelpbutton(),right,left,down,slider_loopafstand,slider_looptijd,buttonsmenu.getloopafstandtext(),buttonsmenu.getlooptijdtext(),exitButton.getExitbutton());

      
        primaryStage.setScene( theScene );
        primaryStage.show();
       
        ///////////////////////////////////////////////////////////////
        //                         Update Scherm                    ///
        ///////////////////////////////////////////////////////////////
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime){
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());        
                gc.drawImage( Images.background, map_x, map_y );
                menu.InteractionCheckbox(bus, metro, tram, primaryStage, theScene, MetroButtonList, BusButtonList,TramButtonList);
                // text loopafstand en tijd
                double newloopafstanddouble = (slider_loopafstand.getValue() * 1.25);
                int newloopafstandint = (int)newloopafstanddouble;
                String newloopafstandtext = Integer.toString(newloopafstandint);
                buttonsmenu.getloopafstandtext().setText(newloopafstandtext + " meter");  
                double newlooptijddouble = (slider_looptijd.getValue() / 66);
                int newlooptijdint = (int)newlooptijddouble;
                String newlooptijdtext = Integer.toString(newlooptijdint);
                buttonsmenu.getlooptijdtext().setText(newlooptijdtext + " minuten");   // 5 km/h // 83m per minuut // 66 pixels per minuut
                               
                for (Button metroo : MetroButtonList){
                    if(metro.isSelected())
                        
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - metroo.getLayoutY()),2) + Math.pow(((menu.getgarX())- metroo.getLayoutX()),2))) <= slider_loopafstand.getValue())
                            &&((Math.sqrt(Math.pow(((menu.getgarY()) - metroo.getLayoutY()),2) + Math.pow(((menu.getgarX())- metroo.getLayoutX()),2))) <= slider_looptijd.getValue()))
                        {metroo.setGraphic(new ImageView(Images.M_image));}
                        else{metroo.setGraphic(new ImageView(Images.metro_imagebw));}
                }
                for (Button tramm : TramButtonList){
                    if(tram.isSelected())
                        
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - tramm.getLayoutY()),2) + Math.pow(((menu.getgarX())- tramm.getLayoutX()),2))) <= slider_loopafstand.getValue())
                            &&((Math.sqrt(Math.pow(((menu.getgarY()) - tramm.getLayoutY()),2) + Math.pow(((menu.getgarX())- tramm.getLayoutX()),2))) <= slider_looptijd.getValue()))
                        {tramm.setGraphic(new ImageView(Images.T_image));}
                        else{tramm.setGraphic(new ImageView(Images.tram_imagebw));}
                }
                for (Button buss : BusButtonList){
                    if(bus.isSelected())
                        
                        if(((Math.sqrt(Math.pow(((menu.getgarY()) - buss.getLayoutY()),2) + Math.pow(((menu.getgarX())- buss.getLayoutX()),2))) <= slider_loopafstand.getValue())
                            &&((Math.sqrt(Math.pow(((menu.getgarY()) - buss.getLayoutY()),2) + Math.pow(((menu.getgarX())- buss.getLayoutX()),2))) <= slider_looptijd.getValue()))
                        {buss.setGraphic(new ImageView(Images.B_image));}
                        else{buss.setGraphic(new ImageView(Images.bus_imagebw));}
                }
                for (Garage garage : new_list){
                    if(comboBox.getValue() == garage.getName() || comboBox.getValue() == "Select Garage" || comboBox.getValue() == "Show All"){    
                        garage.Draw(gc);}
                        if(comboBox.getValue() == garage.getName()){ 
                            menu.setgarY(garage.getPositionY());
                            menu.setgarX(garage.getPositionX());}}               
                
            } 
        }.start();   
    }    
}
