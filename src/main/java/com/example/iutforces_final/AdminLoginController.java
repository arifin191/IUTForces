package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminLoginController {


    @FXML
    private TextField uname, pass;

    @FXML
    Button ad_login,ad_signup, back;

    @FXML
    private Hyperlink  ad_signup_hyp;
    public void verify(String uname, String pass) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
            Statement statement = connection.createStatement();
            uname = "'" + uname + "'";
            ResultSet resultSet = statement.executeQuery("select * from `tst`.`poeple` where name = " + uname);
            int itr = 1;
            String pass_in_db = ""; // the password that is stored in the db aka the correct password
            while (resultSet.next() && itr > 0) {
                --itr;
                pass_in_db = resultSet.getString("password");
            }
            if (pass_in_db.equals(pass)) {
                System.out.println("Congratulations, login successful");
            } else {
                System.out.println("Incorrect pass bitch");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void admin_login(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
        Stage stage = (Stage) ad_login.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
        verify(uname.getText(), pass.getText());
    }


    public void to_ad_signup(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-signup.fxml"));
        Stage stage = (Stage) ad_signup_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

}
