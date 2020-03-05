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
    private Map<String, Color> colormap;
    private Map<String, Image> imagemap;
    private ChoiceBox<String> LiveColorbox;
    private ChoiceBox<String> DeadColorbox;
    private ChoiceBox<String> LiveImagebox;
    private ChoiceBox<String> DeadImagebox;
    private Image alive = new Image("/Images/alive.png");
    private Image skull = new Image("/Images/skull.png");
    private Image liveimage = alive;
    private Image deadimage = skull;
    private static boolean containsImages = false;

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
                Integer row = x;
                Integer col = y;
                rect.setFill(Black);
                rect.setWidth(cellWidth);
                rect.setHeight(cellHeight);
                rect.setOnMouseClicked(e -> checkForClick(mySimulation.getState(row,col),rect));
                pane.add(rect, x, y);
                //for iterating, search for the rects with proper x,y
            }
        }
        group.getChildren().add(pane);
    }

    public void displayStates(Color liveColor, Color DeadColor) {
        for (Node child : pane.getChildren()) {
            Rectangle rec = (Rectangle) child;
            Integer column = GridPane.getColumnIndex(child);
            Integer row = GridPane.getRowIndex(child);
            String state = mySimulation.getState(row, column);
            System.out.println(child.getStyle());
            if(state.equals("dead")){
                rec.setFill(DeadColor);
                if(containsImages) {
                    rec.setFill(new ImagePattern(deadimage));
                }
            }
            else{
                rec.setFill(liveColor);
                if(containsImages) {
                    rec.setFill(new ImagePattern(liveimage));
                }
            }
        }
    }

    public void checkForClick(String state, Rectangle rect){
        if(state.equals("dead")) {
            rect.setFill(livecolor);
        }
        else{
            rect.setFill(deadcolor);
        }
    }

    public void updateStates() {
        mySimulation.step();
        displayStates(livecolor, deadcolor);
    }

    private Node makeButtonPanel () {
        VBox root = new VBox(4);

        HBox LowerButtons = new HBox();
        HBox UpperButtons = new HBox();
        makeColorBoxes();
        makeImageBoxes();

        UpperButtons.getChildren().add(LiveColorbox);
        UpperButtons.getChildren().add(DeadColorbox);
        UpperButtons.getChildren().add(LiveImagebox);
        UpperButtons.getChildren().add(DeadImagebox);
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

    private void makeImageBoxes(){
        imagemap = new HashMap<String, Image>();
        imagemap.put("Alive",alive);
        imagemap.put("Dead",skull);

        LiveImagebox = new ChoiceBox<String>();
        LiveImagebox.getItems().setAll("Live Icon","Alive");
        LiveImagebox.setValue("Live Icon");

        DeadImagebox = new ChoiceBox<String>();
        DeadImagebox.getItems().addAll("Dead Icon","Dead");
        DeadImagebox.setValue("Dead Icon");

        DeadImagebox.setOnAction(e -> ChangeImage(imagemap.get(LiveImagebox.getValue()), imagemap.get(DeadImagebox.getValue())));
        System.out.println(DeadImagebox.getValue());
    }


    private void ChangeImage(Image Live, Image Dead) {
        liveimage = Live;
        deadimage = Dead;
        containsImages = true;
    }


    //create drop down color boxes to change live and dead cell colors
    private void makeColorBoxes() {
        colormap = new HashMap<String, Color>();
        colormap.put("RED",Color.RED);
        colormap.put("BLUE",Color.BLUE);
        colormap.put("GREEN",Color.GREEN);
        colormap.put("BLACK",Color.BLACK);
        colormap.put("WHITE",Color.WHITE);

        LiveColorbox = new ChoiceBox<String>();
        LiveColorbox.getItems().addAll("Live color","RED", "BLUE", "GREEN", "BLACK", "WHITE");
        LiveColorbox.setValue("Live color");

        DeadColorbox = new ChoiceBox<String>();
        DeadColorbox.setValue("Dead color");
        DeadColorbox.getItems().addAll("Dead color", "RED", "BLUE", "GREEN", "BLACK", "WHITE");

        DeadColorbox.setOnShowing(e -> ChangeColor(colormap.get(LiveColorbox.getValue()), colormap.get(DeadColorbox.getValue())));
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
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(file.getPath());
        try {
            mySimulation = new Simulate(propertiesFileReader.readCSVFile(), propertiesFileReader.readGameType());
            //mySimulation = propertiesFileReader.getInitializedSimulation();
            makeGrid(mySimulation.getGridHeight(), mySimulation.getGridWidth());
            displayStates(livecolor, deadcolor);

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
    private void popUpSave() {
        Stage popupstage = new Stage();
        popupstage.setTitle("Save as CSV and Properties File");
        Label label1 = new Label("Enter File Name");
        Label label2 = new Label("Your file name will be saved as a .properties and .CSV file");
        Button saveButton = new Button("Save");
        TextField FileName = new TextField();
        saveButton.setOnAction(e->
            {   try {
                mySimulation.generateSimFile(FileName.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label1,FileName,saveButton,label2);
        layout.setAlignment(Pos.CENTER);

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
