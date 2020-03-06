package cellsociety;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class PopUp{
    private ResourceBundle GOLResourceBundle;

    public PopUp () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    //popup to save properties and csv files
    public void popUpSave(Simulate mySimulation) {
        Stage popupstage = new Stage();
        popupstage.setTitle(GOLResourceBundle.getString("PopUpTitle"));
        Label label1 = new Label(GOLResourceBundle.getString("FileName"));
        Label label2 = new Label(GOLResourceBundle.getString("Save"));
        Button saveButton = new Button(GOLResourceBundle.getString("SaveButton"));
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

}
