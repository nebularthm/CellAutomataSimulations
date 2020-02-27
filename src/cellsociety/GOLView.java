package cellsociety;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class GOLView {

    private Button myNextButton;
    private GOLModel myModel;

    public GOLView (GOLModel model) {
        myModel = model;
    }

    public Scene makeScene (int width, int height) {
        BorderPane group = new BorderPane();
        group.getChildren().add(makeGrid());
        group.setBottom(makeButtonPanel());
        enableButtons();
        // create scene to hold UI
        Scene scene = new Scene(group, width, height);
        // activate CSS styling
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }

    private GridPane makeGrid(){
        GridPane pane = new GridPane();
        pane.setHgap(1);
        pane.setVgap(1);
        int squareSize = 50;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 9; y++) {
                Rectangle rect = new Rectangle();
                //set color of squares
                rect.setWidth(squareSize);
                rect.setHeight(squareSize);
                pane.add(rect, x, y);
            }
        }
        return pane;
    }

    private Node makeButtonPanel () {
        HBox result = new HBox();
        result.getChildren().add(makeButton("Simulate", event -> Simulate()));
        result.getChildren().add(makeButton("Play", event-> Play()));
        result.getChildren().add(makeButton("Pause", event-> Pause()));
        result.getChildren().add(makeButton("Step", event-> Step()));
        result.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

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

    private void Simulate () {
        //start simulation with random initial configuration
    }

    private void Play(){
        //call method in view
        //while isplaying, step and update
    }

    private void Pause(){
        //create boolean for pause v play
        //set the boolean to false
    }

    private void Step(){
        //access boolean. if true dont step, if false, then do while loop in play once
    }
}
