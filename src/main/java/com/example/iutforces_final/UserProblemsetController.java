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

    @FXML private Button download;
    private String passw="123581321345589";
    @FXML
    private TableView<Problemset> table_problemset;

    @FXML
    private TableColumn<Problemset, Integer> col_pid;

    @FXML
    private TableColumn <Problemset, String> col_pname;

    @FXML
    private TableColumn <Problemset, Integer> col_time;
    @FXML
    private TableColumn <Problemset, Integer> col_memory;

    ObservableList<Problemset> problemList = FXCollections.observableArrayList();

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
            problemList.add(new Problemset(
                    resultSet.getInt("problemID"),
                    resultSet.getString("problem_name"),
                    resultSet.getInt("time_limit"),
                    resultSet.getInt("memory_limit")));
            table_problemset.setItems(problemList);
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

    private void loadData() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
        refreshTable();

        col_pid.setCellValueFactory(new PropertyValueFactory<>("problemID"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("problem_name"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time_limit"));
        col_memory.setCellValueFactory(new PropertyValueFactory<>("memory_limit"));
    }

    public void setDownload(ActionEvent event) throws SQLException, IOException {
        Problemset problem = table_problemset.getSelectionModel().getSelectedItem();
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
       // System.out.println(problem.getProblemID());
        pdfs PDF = new pdfs("", problem.getProblem_name(), problem.getProblemID(),  0, 0);
        PDF.readpdfs();
        //String query = "select pdf from `tst`.`problemset` where problemID ="+problem.getProblemID();
        //PreparedStatement preparedStatement = connection.prepareStatement(query);
        //preparedStatement.execute();
        refreshTable();

    }
}
