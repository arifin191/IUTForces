package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable {
    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;
    private String passw="123581321345589";
    @FXML
    private Label cuid, suid, tuname;

    public void readfromdb_and_set() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
            Statement statement = connection.createStatement();
            //String ct = "truncate `tst`.`cinfo`";
            String st = "select * from `tst`.`cinfo`";
            //statement.executeUpdate(ct);
            ResultSet resultSet = statement.executeQuery(st);
            int itr = 1;
            String cid = "", sid = "", tname = "";
            while (resultSet.next() && itr > 0) {
                --itr;
                 cid = resultSet.getString("cid");
                 sid = resultSet.getString("sid");
                 tname = resultSet.getString("teacher");
            }
            //System.out.println(cid);
            //System.out.println(sid);
            //System.out.println(tname);
            cuid.setText(cid);
            suid.setText(sid);
            tuname.setText(tname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void to_us_home() throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource("User-home.fxml"));
        Stage stage = (Stage) us_home.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));


    }

    public void dummy() throws IOException {
        to_us_home();
        to_us_home();
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
    public void loggingout(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.readfromdb_and_set();
    }
}
