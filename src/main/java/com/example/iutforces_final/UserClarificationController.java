package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Vector;

public class UserClarificationController implements Initializable {
    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    @FXML
    private TextArea msg;

    @FXML
    private Button sendbtn;

    @FXML
    private Label msg1, msg2, msg3;

    @FXML private TextArea tt;

    private String passw = "123581321345589";

    public void showclar() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
            Statement statement = connection.createStatement();
            String st = "select * from `tst`.`clari`";
            ResultSet rs = statement.executeQuery(st);
            Vector<String> vc = new Vector<>();
            String to_show = "";
            while (rs.next()) {
                String name = rs.getString("uname");
                String message = rs.getString("message");
                String curmsg = name + ":   " + message;
                to_show = to_show + curmsg + "\n\n";
            }
            //msg1.setText(vc.elementAt(0));
            //msg2.setText(vc.elementAt(1));
            //msg3.setText(vc.elementAt(2));
            System.out.println(to_show);
            tt.setText(to_show);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void send_message(ActionEvent event)
    {
        String to_send = msg.getText();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
            Statement statement = connection.createStatement();
            to_send = "'" + to_send + "'";
            String st = "insert into `tst`.`clari` (uname,message) values (" + UserLoginController.curname + "," + to_send + ");";
            //System.out.println(st);
            statement.executeUpdate(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setText("");
        showclar();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showclar();
    }
}
