package cellsociety;

import java.awt.*;
import java.awt.Dimension;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Initializer extends Application {
    // convenience constants
    public static final String TITLE = "Game of Life Simulation";
    public static final Dimension DEFAULT_SIZE = new Dimension(500, 500);
    private static final double SECOND_DELAY = 1;
    private Timeline myAnimation = new Timeline();
    private Simulate sim;
    GOLView display;
    @Override
    public void start (Stage stage) throws IOException {
        // create program specific components
        CSVFileReader reader = new CSVFileReader("Data/GameOfLife3.csv");
        sim = new Simulate(reader.getHeight(),reader.getWidth(),reader.readStates(),"Game of Life");
        display = new GOLView(sim);
        // give the window a title
        stage.setTitle(TITLE);
        // add our user interface components to Frame and show it
        stage.setScene(display.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
        stage.show();
        // start somewhere, less typing for debugging
        // display.showPage(DEFAULT_START_PAGE);
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();

    }

    private void step(double secondDelay) {
        sim.step();

    }


    public static void main (String[] args) {
        launch(args);
    }
}

