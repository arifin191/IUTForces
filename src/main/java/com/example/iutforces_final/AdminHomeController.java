package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeController {
    @FXML
    private Button submit, ad_home, ad_myprob, ad_probs, ad_manage,ad_stand, ad_status, ad_clar, ad_tut, back ;

    @FXML
    private TextField coursefield, sessionfield, instructorfield;

    public void submitinfo(ActionEvent event) throws IOException
    {
        //System.out.println(coursefield.getText());
        //System.out.println(sessionfield.getText());
        //System.out.println(instructorfield.getText());
    }

    public void to_ad_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
        Stage stage = (Stage) ad_home.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_ad_probset(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-problemset.fxml"));
        Stage stage = (Stage) ad_probs.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }


    public void to_ad_myprob(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-myproblems.fxml"));
        Stage stage = (Stage) ad_myprob.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_manage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-manage.fxml"));
        Stage stage = (Stage) ad_manage.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_stand(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-standings.fxml"));
        Stage stage = (Stage) ad_stand.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_status(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-status.fxml"));
        Stage stage = (Stage) ad_status.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_clar(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-clarification.fxml"));
        Stage stage = (Stage) ad_clar.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_tut(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-tutorial.fxml"));
        Stage stage = (Stage) ad_tut.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

}
