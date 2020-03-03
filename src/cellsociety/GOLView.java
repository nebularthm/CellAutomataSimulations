package cellsociety;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.*;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import javafx.scene.paint.Color;

public class GOLView {
    private static Map<String, String> map;
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
    private ResourceBundle GOLResourceBundle;



    private static final double SECOND_DELAY = 1;

    public GOLView(){
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

        // activate CSS styling
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
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
                rect.setFill(Black);
                rect.setWidth(cellWidth);
                rect.setHeight(cellHeight);
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

            String state = mySimulation.getState(row, column);
            System.out.println(child.getStyle());
            if(state.equals("dead")){

                rec.setFill(Black);
            }
            else{
                rec.setFill(White);

            }
        }
    }


    public void updateStates() {
        mySimulation.step();
        displayStates();
    }

    private Node makeButtonPanel () {
        HBox result = new HBox();
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button1"), event -> getFile()));
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button2"), event -> Simulate()));
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button3"), event-> Play()));
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button4"), event-> Pause()));
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button5"), event-> Step()));
        result.getChildren().add(makeButton(GOLResourceBundle.getString("Button6"), event-> Save()));
        result.setAlignment(Pos.CENTER);
        result.setSpacing(10);
        return result;
    }

    private Button makeButton (String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        final String IMAGEFILE_SUFFIXES =
                String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
        Button result = new Button();
        result.setText(property);
        result.setOnAction(handler);
        result.setId(property);
        return result;
    }

    private Node makeInputPanel () {
        VBox result = new VBox();
        result.getChildren().addAll(makeNavigationPanel());
        return result;
    }

    private Node makeNavigationPanel () {
        HBox result = new HBox();
        // new style way to do set up callback (lambdas)
        myNextButton = makeButton("NextCommand", event -> next());
        result.getChildren().add(myNextButton);
        return result;
    }

    private void next () {

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
        CSVFileReader reader = new CSVFileReader(file.toString());
        try {
            mySimulation = new Simulate(reader);
            makeGrid(mySimulation.getGridHeight(), mySimulation.getGridWidth());
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
        myAnimation.play();

        //call method in view
        //while isplaying, step and update
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

    private void Save(){
        try {
            mySimulation.generateSimFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
