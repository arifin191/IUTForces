package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.PrintWriter;
import java.io.File;

public class UserSubmitController implements Initializable {
    @FXML
    private Button submitcode,us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    @FXML
    private ChoiceBox<String>languageChoiceBox;

    @FXML
    private TextField problemID;

    @FXML
    private TextArea codeEditor;

    public void to_us_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-home.fxml"));
        Stage stage = (Stage) us_home.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_us_probs(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-problemset.fxml"));
        Stage stage = (Stage) us_probs.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_status(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-status.fxml"));
        Stage stage = (Stage) us_status.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_submission(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-submission.fxml"));
        Stage stage = (Stage) us_submission.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_submit(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-submit.fxml"));
        Stage stage = (Stage) us_submit.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_clar(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-clarification.fxml"));
        Stage stage = (Stage) us_clar.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_tut(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-tutorial.fxml"));
        Stage stage = (Stage) us_tut.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_us_stand(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-standings.fxml"));
        Stage stage = (Stage) us_stand.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void setSubmitcode(ActionEvent event)
    {
        String filename = ("temporary//");
        filename+= problemID.getText();
        String pid = problemID.getText();
        if(languageChoiceBox.getSelectionModel().getSelectedItem()=="C++")
        {
            filename += ".cpp";
        }
        else {
            filename += ".c";
        }
        String code= codeEditor.getText();
        try{
            File file = new File(filename);
            if(!file.exists())
            {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(code);
            pw.close();
            //System.out.println("DONE");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        judge judger = new judge(filename, pid);
        int verdict = judger.run();
        if (verdict == -1) {
            System.out.println("Correct Answer");
        } else if (verdict == 0) {
            System.out.println("Compilation error");
        } else if (verdict == 1) {
            System.out.println("Time limit exceeded");
        } else if (verdict == 2) {
            System.out.println("Memory limit exceeded");
        } else if (verdict == 3) {
            System.out.println("Wrong Answer");
        } else if (verdict == 4) {
            System.out.println("File missing");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageChoiceBox.getItems().addAll("C++","C");
    }
}
