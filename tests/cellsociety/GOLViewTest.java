package cellsociety;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GOLViewTest {

    GOLView view = new GOLView();


    @Test
    //test the initialization of the scene for various dimensions
    void makeSceneTest() {
        Scene scene1 = view.makeScene(10,10);
        assertEquals(10, scene1.getHeight());
        assertEquals(10, scene1.getWidth());
        assertEquals(Color.black, scene1.getFill());

        Scene scene2 = view.makeScene(500,500);
        assertEquals(500, scene2.getHeight());
        assertEquals(500, scene2.getWidth());
        assertEquals(Color.black, scene2.getFill());

    }

    @Test
    void displayStatesTest() {

    }

    @Test
    void updateStates() {

    }
}