package cellsociety;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.ColumnOrderDependent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import javafx.scene.paint.Color;

public class GOLView {
    private Color Black = Color.BLACK;
    private Color White = Color.WHITE;
    private Button myNextButton;
    private GOLModel myModel;
    private Simulate mySimulation;
    private GridPane pane;
    private Timeline myAnimation;
    private BorderPane group;
    private double screenHeight;
    private double screenWidth;
    private Color livecolor = Color.BLACK;
    private Color deadcolor = Color.WHITE;
    private ResourceBundle GOLResourceBundle;
    private Map<String, String> colormap;
    private Map<String, ChoiceBox> boxmap;
    private Map<String, Image> imagemap;
    private ChoiceBox<String> LiveImagebox;
    private ChoiceBox<String> DeadImagebox;
    private Image alive = new Image("/Images/alive.png");
    private Image skull = new Image("/Images/skull.png");
    private Image liveimage = alive;
    private Image deadimage = skull;
    private static boolean containsImages = false;
    private List<String> states = new ArrayList<>(3);

    private static final double SECOND_DELAY = 1;

    public GOLView () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    public Scene makeScene (double width, double height) {
        group = new BorderPane();
        group.setBottom(makeButtonPanel());
        enableButtons();
        // create scene to hold UI
        screenHeight = height;
        screenWidth = width;
        Scene scene = new Scene(group, width, height, Color.BLACK);

        //activate CSS styling
        scene.getStylesheets().add("Stylesheet.css");
        Rectangle test = new Rectangle();
        test.getStyleClass().clear();
        return scene;
    }

    private void makeGrid(int numCellsHeight, int numCellsWidth){
        pane = new GridPane();
        pane.setHgap(1);
        pane.setVgap(1);
        double cellWidth;
        double cellHeight;
        if(screenWidth < screenHeight) {
            cellHeight = (screenWidth - numCellsWidth - 30) / numCellsWidth;
        }
        else {
            cellHeight = (screenHeight - numCellsHeight - 30) / numCellsHeight;
        }
        cellWidth = cellHeight;

        for (int x = 0; x < numCellsWidth; x++) {
            for (int y = 0; y < numCellsHeight; y++) {
                Rectangle rect = new Rectangle();
                //set color of squares\
                Integer col = x;
                Integer row = y;
                rect.setFill(Black);
                rect.setWidth(cellWidth);
                rect.setHeight(cellHeight);
                int finalX = x;
                int finalY = y;
                rect.setOnMouseClicked(e -> checkForClick(finalX, finalY, rect));
                pane.add(rect, x, y);
                //for iterating, search for the rects with proper x,y
            }
        }
        group.getChildren().add(pane);
    }

    public void displayStates() {

        for (Node child : pane.getChildren()) {
            Rectangle rec = (Rectangle) child;
            Integer column = GridPane.getColumnIndex(child);
            Integer row = GridPane.getRowIndex(child);
            String state = mySimulation.getState(column, row);

            rec.getStyleClass().clear();
            rec.getStyleClass().add("my-rect-" + colormap.get(state));


//            rec.setFill(paintmap.get(colormap.get(state)));
        }
    }

    //create drop down color boxes to change live and dead cell colors
    private void makeColorBoxes() {
        boxmap = new HashMap<String, ChoiceBox>();
        colormap = new HashMap<String, String>();
        int numberofStates = states.size();

        for(int i = 0;i<numberofStates;i++) {
            int counter = i;
            ChoiceBox<String> colorbox = new ChoiceBox<String>();
            colorbox.getItems().addAll( states.get(i),"red", "blue", "green", "black", "white");
            colorbox.setValue(states.get(i));
            colorbox.setOnAction(e->mapColor(states.get(counter),colorbox.getValue()));
            boxmap.put(states.get(i),colorbox);
        }
    }

    private void mapColor(String state, String color) {
        colormap.put(state, color);
        System.out.println(colormap.get(state));
    }



    public void checkForClick(int x, int y, Rectangle rect) {
        mySimulation.rotateState(x, y);
        String state = mySimulation.getState(x, y);
        rect.getStyleClass().clear();
        rect.getStyleClass().add("my-rect-" + colormap.get(state));
    }

    public void updateStates() {
        mySimulation.step();
        displayStates();
    }

    private Node makeButtonPanel () {
        VBox root = new VBox(4);
        states.add("alive");
        states.add("dead");
        HBox LowerButtons = new HBox();
        HBox UpperButtons = new HBox();
        makeColorBoxes();

        for(int i = 0;i<states.size();i++) {
            UpperButtons.getChildren().add(boxmap.get(states.get(i)));
        }

        UpperButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button0"),event -> displayStates()));
        UpperButtons.setAlignment(Pos.CENTER);
        UpperButtons.setSpacing(10);

        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button1"), event -> getFile()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button2"), event -> Simulate()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button3"), event-> Play()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button4"), event-> Pause()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button5"), event-> Step()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button6"), event-> popUpSave()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button7"), event-> Fast()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button8"), event-> Slow()));
        LowerButtons.setAlignment(Pos.CENTER);
        LowerButtons.setSpacing(10);

        root.getChildren().add(UpperButtons);
        root.getChildren().add(LowerButtons);

        return root;
    }

    private Button makeButton (String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        Button result = new Button();
        result.setText(property);
        result.setOnAction(handler);
        result.setId(property);
        return result;
    }

    private void enableButtons () {

    }

    //Gets file from finder, and uses csv reader
    Stage stage1;
    private void getFile(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage1);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        group.getChildren().remove(pane);
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(file.getPath());
        System.out.println(file.getPath());
        try {
            mySimulation = new Simulate(propertiesFileReader.readCSVFile(), propertiesFileReader.readGameType());
            //mySimulation = propertiesFileReader.getInitializedSimulation();
            makeGrid(mySimulation.getGridHeight(), mySimulation.getGridWidth());
            displayStates();

            //need line that actually loads simulation into grid when file is chosen
        } catch (IOException ex) {

        }
    }

    private void Simulate () {
        //start simulation with random initial configuration
    }

    private void ChangeColor(Color newlive, Color newdead){
        livecolor = newlive;
        deadcolor = newdead;
    }

    private void Play(){
        myAnimation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> updateStates());
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.setRate(1);
        myAnimation.play();
    }

    private void Pause(){
        myAnimation.stop();
        //create boolean for pause v play
        //set the boolean to false
    }

    private void Step(){
        updateStates();
        //access boolean. if true dont step, if false, then do while loop in play once
    }

    //popup to save properties and csv files
    //popup needs to ask for author, title, and description
    private void popUpSave() {
        Stage popupstage = new Stage();
        popupstage.setTitle("Save as CSV and Properties File");
        Label label1 = new Label("Enter File Name");
        Label label2 = new Label("Your file name will be saved as a .properties and .CSV file");
        Button saveButton = new Button("Save");
        TextField FileName = new TextField();
        //get all necessary information (fileName, author, title, description) into a list
        //parameter from simulation and simulation type should be added as well (these methods need to be created)
        //mySimulation.getSimParameter()
        //mySimulation.getSimType()
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label1,FileName,saveButton,label2);
        layout.setAlignment(Pos.CENTER);
        saveButton.setOnAction(e->
            {   try {
                //PropertiesFileGenerator fileGenerator = new PropertiesFileGenerator(savingInfoList)
                //fileGenerator.createPropertiesFile()
                mySimulation.generateSimFile(FileName.getText());
                popupstage.close();
                //need some way for layout to go away upon clicking save button
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });


        Scene scene = new Scene(layout, 500, 350);

        popupstage.setScene(scene);
        popupstage.show();
    }

    private void Fast(){
        myAnimation.setRate(2);
    }

    private void Slow(){
        myAnimation.setRate(0.5);
    }
}