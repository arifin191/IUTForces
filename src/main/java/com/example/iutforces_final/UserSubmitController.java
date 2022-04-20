package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.io.PrintWriter;
import java.io.File;

import static com.example.iutforces_final.UserLoginController.curname;

public class UserSubmitController implements Initializable {
    @FXML
    private Button submitcode,us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    @FXML
    private ChoiceBox<String>languageChoiceBox;

    @FXML
    private TextField problemID;

    @FXML
    private TextArea codeEditor;

    @FXML
    private Label verdictLabel;

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

    public String getdate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String getusername(String uid) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
        Statement statement = connection.createStatement();
        //uid = "'" + uid + "'";
        ResultSet rs = statement.executeQuery("SELECT * FROM `tst`.`poeple` where name = " + uid);
        rs.next();
        return rs.getString("name");
    }

    public String getpname(String pid) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
            Statement statement = connection.createStatement();
            //pid = "'" + pid + "'";
            ResultSet rs = statement.executeQuery("SELECT * FROM `tst`.`problemset` where problemID = " + pid);
            rs.next();
            return rs.getString("problem_name");
        } catch (Exception e){
            return "-2";
        }
    }

    public void setSubmitcode(ActionEvent event) throws SQLException {
        String filename = ("temporary//");
        String uid = curname;
        filename += uid + "_" + problemID.getText();
        String pid = problemID.getText();
        String lang = "";
        if(languageChoiceBox.getSelectionModel().getSelectedItem()=="C++")
        {
            filename += ".cpp";
            lang = "C++";
        }
        else {
            lang = "C";
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
        //System.out.println("PANDAAA");
        //String uname = getusername(uid);
        //System.out.println("PAAAAAANDDDAAAAAA");
        String pname = getpname(pid);
        //System.out.println("THE NAME IS :" + pname);
        judge judger = new judge(filename, pname, pid);
        int verdict = judger.getverdict();
        long tm = judger.gettime();
        String vstr = "";
        String dt = getdate();
        //System.out.println(dt);
        if (pname.equals("-2")) {
            verdict = 100;
            vstr = "No problem with given ID";
        } else if (verdict == -1) {
            vstr = "Correct Answer";
        } else if (verdict == 0) {
            vstr = "Compilation error";
        } else if (verdict == 1) {
            vstr = "Time limit exceeded";
        } else if (verdict == 2) {
            vstr = "Memory limit exceeded";
        } else if (verdict == 3) {
            vstr = "Wrong Answer";
        }

        verdictLabel.setText(vstr);

        if (verdict != 100) {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
            Statement statement = connection.createStatement();

            String sql =  "INSERT INTO `tst`.`status` (stname, pname, stime, etime, language, verdict) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = null;
            pst = connection.prepareStatement(sql);
            pst.setString(1, uid);
            pst.setString(2, pname);
            pst.setString(3, dt);
            pst.setString(4, String.valueOf(tm));
            pst.setString(5, lang);
            pst.setString(6, vstr);
            //System.out.println(pst);
            pst.executeUpdate();
        }
        //System.out.println(vstr + uid + pname);

        //send stuffs to database


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageChoiceBox.getItems().addAll("C++","C");
    }
}
