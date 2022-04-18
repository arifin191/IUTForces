package com.example.iutforces_final;

import javafx.beans.Observable;
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

public class UserProblemsetController implements Initializable {
    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    private String passw="12345";
    @FXML
    private TableView <Problemset> table_problemset;

    @FXML
    private TableColumn <Problemset, Integer> col_pid;

    @FXML
    private TableColumn <Problemset, String> col_pname;

    @FXML
    private TableColumn <Problemset, String> col_author;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Problemset problemset = null;

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
        problemList.clear();
        query = "select * from `tst`.`problemset`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Integer i = resultSet.getInt("problemID");
            String s1 = resultSet.getString("problem_name");
            String s2 = resultSet.getString("problem_author");
            //System.out.println(i);
            //System.out.println(s1);
            //System.out.println(s2);
            problemList.add(new Problemset(i, s1, s2));
            table_problemset.setItems(problemList);
        }

    }



    ObservableList <Problemset> problemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
        refreshTable();

        col_pid.setCellValueFactory(new PropertyValueFactory<>("problemID"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("problem_name"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("problem_author"));
    }
}
