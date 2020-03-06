package cellsociety;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class PopUp{
    public ResourceBundle GOLResourceBundle;

    public PopUp () {
        GOLResourceBundle = ResourceBundle.getBundle("cellsociety.Resources.GOLView");
    }

    public void popUpSave(Simulate mySimulation) {
        Stage popupstage = new Stage();
        popupstage.setTitle(GOLResourceBundle.getString("PopUpTitle"));
        Label label1 = new Label(GOLResourceBundle.getString("FileName"));
        Label label2 = new Label(GOLResourceBundle.getString("Save"));
        Button saveButton = new Button(GOLResourceBundle.getString("SaveButton"));
        TextField FileName = new TextField();
        TextField Title = new TextField();
        TextField Author = new TextField();
        TextField Description = new TextField();

        FileName.setMaxWidth(400);
        Title.setMaxWidth(400);
        Author.setMaxWidth(400);
        Description.setMaxWidth(400);

        FileName.setPromptText(GOLResourceBundle.getString("Filename"));
        Title.setPromptText(GOLResourceBundle.getString("title"));
        Author.setPromptText(GOLResourceBundle.getString("Author"));
        Description.setPromptText(GOLResourceBundle.getString("Description"));

        //get all necessary information (fileName, author, title, description) into a list
        //parameter from simulation and simulation type should be added as well (these methods need to be created)
        //mySimulation.getSimParameter()
        //mySimulation.getSimType()
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label1,FileName,Title,Author, Description,saveButton,label2);
        layout.setAlignment(Pos.CENTER);
        saveButton.setOnAction(e->
        {   try {
            popupstage.close();
            List simInfo = new ArrayList();
            simInfo.add(Title.getText());
            simInfo.add(Author.getText());
            simInfo.add(Description.getText());
            mySimulation.generateSimFile(FileName.getText());
            simInfo.add("data/" + FileName.getText());
            simInfo.add(mySimulation.getGameType());
            //simInfo.add(mySimulation.getParameter());
            PropertiesFileGenerator fileGenerator = new PropertiesFileGenerator(simInfo);
            fileGenerator.createPropertiesFile(FileName.getText());


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
