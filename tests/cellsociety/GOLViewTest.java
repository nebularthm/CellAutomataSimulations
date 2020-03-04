package cellsociety;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class GOLViewTest extends DukeApplicationTest {
    private GridPane pane;
    GOLView view = new GOLView();
    Scene myScene;
    Initializer init = new Initializer();

    @Override
    public void start (Stage stage) throws IOException {
        // create game's scene with all shapes in their initial positions and show it
        init.start(stage);
        myScene = view.makeScene(50,50);
        stage.setScene(myScene);
        stage.show();
    }

    @Test
    //test the initialization of the scene for various dimensions
    void makeSceneTest() {
        assertEquals(10, myScene.getHeight());
        assertEquals(10, myScene.getWidth());
        assertEquals(Color.black, myScene.getFill());

    }

    @Test
    void displayStatesTest() {


    }

    @Test
    void updateStates() {

    }
}