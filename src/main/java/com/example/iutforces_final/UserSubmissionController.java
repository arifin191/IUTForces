package com.example.iutforces_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//import static com.example.iutforces_final.UserLoginController.curname;

public class UserSubmissionController implements Initializable {
    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;
    @FXML
    private TableView<Status> table_status;

    @FXML
    private TableColumn<Status, String> col_id;
    @FXML
    private TableColumn<Status, String> col_sub_time;
    @FXML
    private TableColumn<Status, String> col_lang;
    @FXML
    private TableColumn<Status, String> col_ver;
    @FXML
    private TableColumn<Status, String> col_exec_time;
    @FXML
    private TableColumn<Status, String> col_pname;

    private String passw = "123581321345589";

    ObservableList<Status> statuslist = FXCollections.observableArrayList();

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Status status = null;

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

    @FXML private void refreshTable() throws SQLException {
        statuslist.clear();
        query = "select * from  `tst`.`status` where stname =" + UserLoginController.curname;
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        //Status status = null;

        while(resultSet.next()) {
            System.out.println(resultSet.getString(1));
            String x = resultSet.getString(1);
            if (x == null) x = "NULL";
            //if (!x.equals(UserLoginController.curname)) continue;
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(6));
            statuslist.add(new Status(
                    x,
                    resultSet.getString("pname"),
                    resultSet.getString("stime"),
                    resultSet.getString("etime"),

                    resultSet.getString("verdict"),
                    resultSet.getString("language")
            ));
            table_status.setItems(statuslist);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
        refreshTable();

        col_id.setCellValueFactory(new PropertyValueFactory<>("stname"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        col_sub_time.setCellValueFactory(new PropertyValueFactory<>("stime"));
        col_exec_time.setCellValueFactory(new PropertyValueFactory<>("etime"));
        col_lang.setCellValueFactory(new PropertyValueFactory<>("language"));
        col_ver.setCellValueFactory(new PropertyValueFactory<>("verdict"));


    }
}
