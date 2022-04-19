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
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminLoginController {


    @FXML
    private TextField uname, pass;
    private String passw = "123581321345589";
    @FXML
    Button ad_login,ad_signup, back;

    @FXML
    private Label invalid;

    @FXML
    private Hyperlink  ad_signup_hyp;

    @FXML
    private Hyperlink us_login_hyp, us_signup_hyp, ad_login_hyp;

    public int verify(String uname, String pass) {
        int ret = 1;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
            Statement statement = connection.createStatement();
            uname = "'" + uname + "'";
            ResultSet resultSet = statement.executeQuery("select * from `tst`.`poeple` where name = " + uname);
            int itr = 1;
            Integer hsh = pass.hashCode();
            String hash = hsh.toString();
            System.out.println(hsh);
            String pass_in_db = ""; // the password that is stored in the db aka the correct password
            while (resultSet.next() && itr > 0) {
                --itr;
                pass_in_db = resultSet.getString("password");
                //System.out.println(resultSet.getString("password"));
            }
            if (pass.equals("") || uname.equals("")) {
                ret = 1;
            } else if (pass_in_db.equals(hash)) {
                ret = 0;
                //System.out.println("Congratulations, login successful");
            } else {
                ret = 2;
                //System.out.println("Incorrect pass bitch");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ret =0;
        return ret;
    }

    /*public void to_ad_login_hyp(int status) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-login.fxml"));
        Stage stage = (Stage) ad_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
        System.out.println(status);
        if (status == 2) invalid.setText(   "WRONG PASS BITCH");
        else if (status == 1) invalid.setText("NO FIELD CAN BE EMPTY");
        System.out.println(status);
    }*/

    public void admin_login(ActionEvent event) throws IOException
    {
        int status = verify(uname.getText(), pass.getText());
        if(status==0)
        {
            Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
            Stage stage = (Stage) ad_login.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 720));
        }
        else if(status==1)
        {
            invalid.setText("NO FIELD CAN BE EMPTY!");
        }
        else if(status==2)
        {
            invalid.setText("WRONG CREDENTIALS!");
        }

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
