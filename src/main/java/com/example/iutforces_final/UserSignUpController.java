package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserSignUpController {
    @FXML
    private TextField name_field, pass_field, conpass_field;

    @FXML
    private Button signup, back;
    @FXML
    private Hyperlink us_login_hyp;

    @FXML
    private Label update;


    private String passw="123581321345589";


    public void to_us_login_hyp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-login.fxml"));
        Stage stage = (Stage) us_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void setSignup(ActionEvent event) throws IOException
    {
        String uname = name_field.getText();
        String pass1 = pass_field.getText();
        String pass2 = conpass_field.getText();
        if(!pass1.equals(pass2))
        {
            update.setText("The Passwords don't match!");
        }
        else if(uname.equals("") || pass1.equals("") || pass2.equals("") )
        {
            update.setText("Don't leave any field empty!");
        }
        else{
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
                Statement statement = connection.createStatement();
                Integer temp = pass1.hashCode();
                pass1 = temp.toString();
                //uname = "'" + uname + "'";
                //pass1 = "'" + pass1 + "'";
                String z = "0";
                String st = "insert into `tst`.`poeple` (name,password, isadmin) values (" + uname + "," + pass1 + "," + z + ");";
                //System.out.println(st);
                statement.executeUpdate(st);
                to_home(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
