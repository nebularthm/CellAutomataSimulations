package cellsociety;
import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class GOLView {

    private Button myNextButton;
    private GOLModel myModel;
    private Container contents;
    private JButton[][] squares = new JButton[8][8];

    public GOLView (GOLModel model) {
        myModel = model;
    }

    public Scene makeScene (int width, int height) {
        BorderPane root = new BorderPane();
        root.setLeft(makeButtonPanel());
//        root.getChildren().add(contents);
        enableButtons();
        // create scene to hold UI
        Scene scene = new Scene(root, width, height);
        // activate CSS styling
        //scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }


    private Node makeButtonPanel () {
        HBox result = new HBox();
        result.getChildren().add(makeButton("Simulate", event -> Simulate()));
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

    private void update (URL url) {
        enableButtons();
    }

    private void enableButtons () {

    }

    private void Simulate () {

    }

}
