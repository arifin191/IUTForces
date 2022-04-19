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

public class AdminStandingController implements Initializable {
    @FXML
    private Button save, ad_home, ad_myprob, ad_probs, ad_manage,ad_stand, ad_status, ad_clar, ad_tut, back ;

    @FXML
    private TableView<Rank> table_rank;

    @FXML
    private TableColumn<Rank, Integer> col_rank;
    @FXML
    private TableColumn<Rank, String> col_name;
    @FXML
    private TableColumn<Rank, Integer> col_count;

    private String passw = "123581321345589";

    ObservableList<Rank> ranklist = FXCollections.observableArrayList();

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Rank rank = null;

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

    @FXML private void refreshTable() throws SQLException {
        ranklist.clear();
        query = "SELECT row_number() over(order by COUNT(*) DESC) ranking, stname, COUNT(*) AS count FROM status where verdict = \"Correct Answer\" GROUP BY stname ORDER BY count DESC";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            System.out.println(resultSet.getString(1));
            //String x = resultSet.getString(1);
            //if (!x.equals(UserLoginController.curname)) continue;
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            //System.out.println(resultSet.getString(4));
            //System.out.println(resultSet.getString(5));
            //System.out.println(resultSet.getString(6));
            ranklist.add(new Rank(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
            table_rank.setItems(ranklist);
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

        col_rank.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("stname"));
        col_count.setCellValueFactory(new PropertyValueFactory<>("count"));


    }

}
