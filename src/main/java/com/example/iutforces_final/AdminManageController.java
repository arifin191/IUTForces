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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.action.ActionGroup;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminManageController implements Initializable {
    @FXML
    private Button save, ad_home, ad_myprob, ad_probs, ad_manage,ad_stand, ad_status, ad_clar, ad_tut, back ;

    @FXML
    private Button add,del, edit;

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



    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Problemset problemset = null;

    @FXML private void refreshTable() throws SQLException {
        problemList.clear();
        query = "select * from `tst`.`problemset`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            Integer i = resultSet.getInt("problemID");
            String s1 = resultSet.getString("problem_name");
            Integer s2 = resultSet.getInt("time_limit");
            Integer s3 = resultSet.getInt("memory_limit");
            //System.out.println(i);
            //System.out.println(s1);
            //System.out.println(s2);
            problemList.add(new Problemset(i, s1, s2, s3));
            table_problemset.setItems(problemList);
        }

    }



    ObservableList<Problemset> problemList = FXCollections.observableArrayList();

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

    public void to_addProblem(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("AddProblem.fxml"));
        Stage stage = new Stage();
        add.getScene().getWindow();
        stage.setTitle("Add Problem");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void to_ad_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
        Stage stage = (Stage) ad_home.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_ad_probset(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-problemset.fxml"));
        Stage stage = (Stage) ad_probs.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }


    public void to_ad_myprob(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-myproblems.fxml"));
        Stage stage = (Stage) ad_myprob.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_manage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-manage.fxml"));
        Stage stage = (Stage) ad_manage.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_stand(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-standings.fxml"));
        Stage stage = (Stage) ad_stand.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_status(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-status.fxml"));
        Stage stage = (Stage) ad_status.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_clar(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-clarification.fxml"));
        Stage stage = (Stage) ad_clar.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_tut(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-tutorial.fxml"));
        Stage stage = (Stage) ad_tut.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void delete(ActionEvent event) throws SQLException {
        Problemset problem = table_problemset.getSelectionModel().getSelectedItem();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
        String query = "delete from `tst`.`problemset` where problemID ="+problem.getProblemID();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        refreshTable();

    }
}
