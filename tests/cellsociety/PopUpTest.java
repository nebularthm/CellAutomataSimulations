package cellsociety;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;
import util.DukeApplicationTest;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class PopUpTest extends DukeApplicationTest {

    CSVFileReader reader = new CSVFileReader("data/GameOfLife4.csv");
    Simulate mySimulation = new Simulate(reader, "GOL");
    PopUp popUp = new PopUp();
    ResourceBundle bundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");

    PopUpTest() throws IOException {
    }

    @Override
    public void start (Stage stage) throws IOException {
        // create game's scene with all shapes in their initial positions and show it
        popUp.popUpSave(mySimulation);
    }

    /*@Test
    void PopUpTest() {

        Label label1 = lookup("#SaveInstructions").query();
        Label label2 = lookup("#SaveInfo").query();
        Button saveButton = lookup("#SaveButton").query();
        TextField FileName = lookup("#FileNameBox").query();
        TextField Title = lookup("#TitleBox").query();
        TextField Author = lookup("#AuthorBox").query();
        TextField Description = lookup("#DescriptionBox").query();

        assertEquals(Title.getPromptText(), bundle.getString("title"));

    }*/
}