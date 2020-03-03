package cellsociety;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.awt.*;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;


class InitializerTest extends DukeApplicationTest {
    private GOLView testdisplay;
    @Override
    public void start(Stage stage){
        ResourceBundle GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
        // create program specific components
        testdisplay = new GOLView();
        // give the window a title
        stage.setTitle(GOLResourceBundle.getString("Title"));
        // add our user interface components to Frame and show it
        stage.setScene(testdisplay.makeScene(400,400));
        //display.updateStates();
        stage.show();
    }
    @Test
    void start() {
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
}