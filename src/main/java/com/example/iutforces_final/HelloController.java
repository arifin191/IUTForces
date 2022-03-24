package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloController {
    @FXML
    private Button save, ad_home, ad_myprob, ad_probs, ad_manage,ad_stand, ad_status, ad_clar, ad_tut, back , ad_login,ad_signup;

    @FXML
    private Button us_home, us_probs, us_status, us_submission, us_stand, us_submit, us_clar, us_tut, us_login, logout;

    @FXML
    private Button us_opt, ad_opt;

    @FXML
    private Hyperlink us_login_hyp, us_signup_hyp, ad_login_hyp, ad_signup_hyp;

    @FXML
    private Label course_code, session, insructor, date, dummy;

    @FXML
    private TextField coursefield, sessionfield, instructorfield;

    @FXML
    private DatePicker datePicker;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void update(ActionEvent event) {
        String crs, ses, inst;
        crs = coursefield.getText();
        ses = sessionfield.getText();
        inst = instructorfield.getText();
        course_code.setText(crs);
        session.setText(ses);
        insructor.setText(inst);
    }

    public void set_date(ActionEvent event)
    {
        LocalDate today = datePicker.getValue();
        date.setText(today.toString());
    }

    public void to_us_signup(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-signup.fxml"));
        Stage stage = (Stage) us_signup_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_us_login_hyp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-login.fxml"));
        Stage stage = (Stage) us_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_signup(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-signup.fxml"));
        Stage stage = (Stage) ad_signup_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_ad_login_hyp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-login.fxml"));
        Stage stage = (Stage) ad_login_hyp.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_logout(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void user_login(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-home.fxml"));
        Stage stage = (Stage) us_login.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void admin_login(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
        Stage stage = (Stage) ad_login.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
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


    public void to_admin(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-login.fxml"));
        Stage stage = (Stage) ad_opt.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("program-home.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }


    public void to_user(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("User-login.fxml"));
        Stage stage = (Stage) us_opt.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }

    public void to_ad_probset(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-problemset.fxml"));
        Stage stage = (Stage) ad_probs.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 720));
    }
    public void to_ad_home(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin-home.fxml"));
        Stage stage = (Stage) ad_home.getScene().getWindow();
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

}