package cellsociety;

import java.awt.*;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Brian Li
 * */

public class Initializer extends Application {
    // convenience constants
    public static final String TITLE = "Game of Life Simulation";
    public static final Dimension DEFAULT_SIZE = new Dimension(750, 900);
    private Timeline myAnimation = new Timeline();
    private Simulate sim;
    GOLView display;

    @Override
    /**
     * this method sets up the stage and displays the scene
     * @param stage
     * @return
     */
    public void start (Stage stage) throws IOException {
        ResourceBundle GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
        // create program specific components
        display = new GOLView();
        // give the window a title
        stage.setTitle(GOLResourceBundle.getString("Title"));
        // add our user interface components to Frame and show it
        stage.setScene(display.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
        //display.updateStates();
        stage.show();
        // start somewhere, less typing for debugging
        // display.showPage(DEFAULT_START_PAGE);
    }

    public GOLView getDisplay(){
        return display;
    }


    /**
     * this is the main method that launches the simulation
     */
    public static void main (String[] args) {
        launch(args);
    }
}

