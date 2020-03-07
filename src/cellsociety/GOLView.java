package cellsociety;
import java.io.File;
import java.util.*;
import java.util.List;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.scene.paint.Color;


public class GOLView {
    private Color Black = Color.BLACK;
    private Color White = Color.WHITE;
    private Button myNextButton;
    private GOLModel myModel;
    private Simulate mySimulation;
    private GridPane pane;
    private VBox myVBox;
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
    private boolean testMode;
    private String testFilePath;
    private  double animationRate;

    private static final double SECOND_DELAY = 1;

    public GOLView () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    PopUp myPopUp = new PopUp();
    public Scene makeScene (double width, double height) {
        group = new BorderPane();
        group.setBottom(makeButtonPanel());
        enableButtons();
        group.setId("pane");
        // create scene to hold UI
        screenHeight = height;
        screenWidth = width;
        Scene scene = new Scene(group, width, height, Color.BLACK);

        //activate CSS styling
        scene.getStylesheets().add("Stylesheet.css");

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

    private Node makeButtonPanel() {
        myVBox = new VBox(4);
        HBox LowerButtons = new HBox();


        Button fileButton = makeButton(GOLResourceBundle.getString("Button1"), event -> getFile());

        LowerButtons.getChildren().add(fileButton);
        Button simButton = makeButton(GOLResourceBundle.getString("Button2"), event -> Simulate());

        LowerButtons.getChildren().add(simButton);
        Button playButton = makeButton(GOLResourceBundle.getString("Button3"), event-> Play());
        LowerButtons.getChildren().add(playButton);
        Button pauseButon = makeButton(GOLResourceBundle.getString("Button4"), event-> Pause());

        LowerButtons.getChildren().add(pauseButon);
        Button stepButton = makeButton(GOLResourceBundle.getString("Button5"), event-> Step());

        LowerButtons.getChildren().add(stepButton);
        Button saveButton = makeButton(GOLResourceBundle.getString("Button6"), event-> myPopUp.popUpSave(mySimulation));

        LowerButtons.getChildren().add(saveButton);
        Button fastButton = makeButton(GOLResourceBundle.getString("Button7"), event-> Fast());

        LowerButtons.getChildren().add(fastButton);
        Button slowButton = makeButton(GOLResourceBundle.getString("Button8"), event-> Slow());

        LowerButtons.getChildren().add(slowButton);

        LowerButtons.setAlignment(Pos.CENTER);
        LowerButtons.setSpacing(10);


        myVBox.getChildren().add(LowerButtons);

        return myVBox;
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
        File file = null;
        if(!testMode){
                    file = fileChooser.showOpenDialog(stage1);
        }
        else{
            file = new File(testFilePath);
        }

        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        group.getChildren().remove(pane);
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(file.getPath());
        try {
            mySimulation = new Simulate(propertiesFileReader.readCSVFile(), propertiesFileReader.readGameType());
            makeGrid(mySimulation.getGridHeight(), mySimulation.getGridWidth());
            HBox UpperButtons = new HBox();
            states = mySimulation.getUnmodifiablePossibleStates();
            makeColorBoxes();

            for(int i = 0;i<states.size();i++) {
                UpperButtons.getChildren().add(boxmap.get(states.get(i)));
            }

            UpperButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button0"),event -> displayStates()));
            UpperButtons.setAlignment(Pos.CENTER);
            UpperButtons.setSpacing(10);
            myVBox.getChildren().add(UpperButtons);

            displayStates();

            //need line that actually loads simulation into grid when file is chosen
        } catch (IOException ex) {

        }
    }

    private void Simulate () {
        //start simulation with random initial configuration
    }

    private void Play(){
        myAnimation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> updateStates());
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.setRate(1);
        animationRate = myAnimation.getRate();
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

    private void Fast(){
        myAnimation.setRate(2);
        animationRate = myAnimation.getRate();
    }

    private void Slow(){
        myAnimation.setRate(0.5);
        animationRate = myAnimation.getRate();
    }



    //These methods are for tests


    public Simulate getMySimulation() {
        return mySimulation;
    }
    public void setTestMode(boolean testin){
        testMode = testin;
    }

    public void setTestFilePath(String testFilePath) {
        this.testFilePath = testFilePath;
    }

    public double getAnimationRate() {
        return animationRate;
    }
}