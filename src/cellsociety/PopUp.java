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
    public ResourceBundle GOLResourceBundle;

    public PopUp () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    void popUpSave(Simulate mySimulation) {
        Stage popupstage = new Stage();
        popupstage.setTitle("Save as CSV and Properties File");
        Label label1 = new Label("Enter File Name");
        Label label2 = new Label("Your file name will be saved as a .properties and .CSV file");
        Button saveButton = new Button("Save");
        TextField FileName = new TextField();
        //get all necessary information (fileName, author, title, description) into a list
        //parameter from simulation and simulation type should be added as well (these methods need to be created)
        //mySimulation.getSimParameter()
        //mySimulation.getSimType()
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label1,FileName,saveButton,label2);
        layout.setAlignment(Pos.CENTER);
        saveButton.setOnAction(e->
        {   try {
            //PropertiesFileGenerator fileGenerator = new PropertiesFileGenerator(savingInfoList)
            //fileGenerator.createPropertiesFile()
            mySimulation.generateSimFile(FileName.getText());
            popupstage.close();
            //need some way for layout to go away upon clicking save button
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        });


        Scene scene = new Scene(layout, 500, 350);

        popupstage.setScene(scene);
        popupstage.show();
    }


}
