package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class HelloController {
    @FXML
    private Button save, ad_home, ad_myprob, ad_probs, ad_manage,ad_stand, ad_status, ad_clar, ad_tut, back ;

    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    @FXML
    private Button us_opt, ad_opt;

    @FXML
    private Hyperlink us_login_hyp, us_signup_hyp, ad_login_hyp;




    private Stage stage;
    private Scene scene;
    private Parent root;

    public void to_us_login_hyp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-login.fxml"));
        Stage stage = (Stage) us_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_login_hyp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-login.fxml"));
        Stage stage = (Stage) ad_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_logout(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void user_login(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-home.fxml"));
        Stage stage = (Stage) us_login.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }



    public void to_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }


    public void to_user(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-login.fxml"));
        Stage stage = (Stage) us_opt.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_admin(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-login.fxml"));
        Stage stage = (Stage) ad_opt.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }



}