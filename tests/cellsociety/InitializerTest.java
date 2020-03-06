package cellsociety;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;


class InitializerTest extends DukeApplicationTest {
    ResourceBundle GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    private GOLView testdisplay;
    private  Simulate testSimulate;
    private static final String GOLTESTPATH = ("src\\cellsociety\\Configurations\\GOLBlinker.properties");
    private Button fileButton;
    private Button playButton;
    private Button stepButton;
    private Button fastButton;
    private Button slowButton;

    @Override
    public void start(Stage stage){
        ResourceBundle GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
        // create program specific components
        testdisplay = new GOLView();
        // give the window a title
        stage.setTitle(GOLResourceBundle.getString("Title"));
        // add our user interface components to Frame and show it
        testdisplay.setTestFilePath(GOLTESTPATH);
        testdisplay.setTestMode(true);
        Scene testScene =testdisplay.makeScene(400,400);
        stage.setScene(testScene);
        //display.updateStates();
        stage.show();
    }




    @Test
    void main() {
    }

    @Test
    void testMakeScene(){
        Scene scene1 = testdisplay.makeScene(10,10);
        assertEquals(10, scene1.getHeight());
        assertEquals(10, scene1.getWidth());


        Scene scene2 = testdisplay.makeScene(500,500);
        assertEquals(500, scene2.getHeight());
        assertEquals(500, scene2.getWidth());
    }
    @Test
    void testSimulation(){
        fileButton = lookup( GOLResourceBundle.getString("Button1")).query();

        System.out.println(fileButton.toString());
        clickOn(fileButton);
        testSimulate = testdisplay.getMySimulation();
        assertNotEquals(null,testSimulate);//this tests that choosefile work because the sim is not null, mns file was chosen
        System.out.println(testdisplay.getAnimationRate());
    }
    @Test
    void testStepAndPlay() throws IOException {
        CSVFileReader reader = new CSVFileReader("data/GameOfLife1.csv");
        String [][] dummyStates = reader.readStates();
        playButton = lookup( GOLResourceBundle.getString("Button3")).query();
        stepButton = lookup( GOLResourceBundle.getString("Button5")).query();
        fileButton = lookup( GOLResourceBundle.getString("Button1")).query();
        clickOn(fileButton);
        assertEquals(dummyStates[2][3],testSimulate.getState(2,3));
        clickOn(playButton);
        assertNotEquals(dummyStates[2][3],testSimulate.getState(2,3));
    }
    @Test
    void testAnimationRate(){
        playButton = lookup( GOLResourceBundle.getString("Button3")).query();
        stepButton = lookup( GOLResourceBundle.getString("Button5")).query();
        fileButton = lookup( GOLResourceBundle.getString("Button1")).query();
        fastButton = lookup( GOLResourceBundle.getString("Button7")).query();
        slowButton = lookup( GOLResourceBundle.getString("Button8")).query();
        clickOn(fileButton);
    }

}