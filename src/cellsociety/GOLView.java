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

/**
 * @author Brian Li
 * */

/**
 * this class sets up the GUI View for the simulation and includes a Grid of cells in addition to a button panel to control the simulation
 */
public class GOLView {
    private Color Black = Color.BLACK;
    private Simulate mySimulation;
    private GridPane pane;
    private VBox myVBox;
    private Timeline myAnimation;
    private BorderPane group;
    private double screenHeight;
    private double screenWidth;
    private ResourceBundle GOLResourceBundle;
    private Map<String, String> colormap;
    private Map<String, ChoiceBox> boxmap;
    private List<String> states = new ArrayList<>(3);

    private static final double SECOND_DELAY = 1;

    public GOLView () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    PopUp myPopUp = new PopUp();

    /**
     * this method creates the Scene with the Grid of Cells and Button panel
     * @param width, height
     * @return
     */
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

    /**
     * this method creates the GridPane of Rectangles
     * @param numCellsHeight, numCellsWidth
     * @return
     */
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
                //set color of squares
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

    /**
     * this method displays the states of each individual cell
     */
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

    /**
     * this method creates drop down color boxes to change live and dead cell colors
     */
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

    /**
     * this method maps a color to its corresponding cell state
     * @param state, color
     * @return
     */
    private void mapColor(String state, String color) {
        colormap.put(state, color);
        System.out.println(colormap.get(state));
    }

    /**
     * this method checks if a cell is clicked on
     * @param x,y,rect
     * @return
     */
    public void checkForClick(int x, int y, Rectangle rect) {
        mySimulation.rotateState(x, y);
        String state = mySimulation.getState(x, y);
        rect.getStyleClass().clear();
        rect.getStyleClass().add("my-rect-" + colormap.get(state));
    }

    /**
     * this method updates the states of the cells
     * @return
     */
    public void updateStates() {
        mySimulation.step();
        displayStates();
    }

    /**
     * this method creates the panel of buttons for the simulation's GUI
     * @return
     */
    private Node makeButtonPanel() {
        myVBox = new VBox(4);
        HBox LowerButtons = new HBox();

        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button1"), event -> getFile()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button2"), event -> Simulate()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button3"), event-> Play()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button4"), event-> Pause()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button5"), event-> Step()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button6"), event-> myPopUp.popUpSave(mySimulation)));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button7"), event-> Fast()));
        LowerButtons.getChildren().add(makeButton(GOLResourceBundle.getString("Button8"), event-> Slow()));
        LowerButtons.setAlignment(Pos.CENTER);
        LowerButtons.setSpacing(10);

        myVBox.getChildren().add(LowerButtons);

        return myVBox;
    }

    /**
     * this method creates a single button based on the button's property
     * @param property, handler
     * @return
     */
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

    /**
     * this method accessed the File that is selected
     * @param numCellsHeight, numCellsWidth
     * @return
     */
    //Gets file from finder, and uses csv reader
    Stage stage1;
    private void getFile(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage1);
        if (file != null) {
            openFile(file);
        }
    }

    /**
     * this method opens and reads the file that is selected
     * @param file
     * @return
     */
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
    }

    private void Slow(){
        myAnimation.setRate(0.5);
    }

}