package com.example.iutforces_final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddProblemController {


    @FXML
    private Button problem_btn, input_btn, output_btn, submit;

    @FXML
    private TextField name_field, time_field, memory_field, id_field;

    public String getname(String problem) {
        String ret = "";
        for (int i = problem.length() - 1; i > 0; --i) {
            char ch = problem.charAt(i);
            if (Character.isWhitespace(ch)) {
                ret += "_";
            } else if (Character.isDigit(ch) || Character.isAlphabetic(ch) || ch == '.' || ch == '_') {
                ret += ch;
            } else {
                break;
            }
        }
        String name = "";
        for (int i = ret.length() - 1; i >= 0; --i) {
            name += ret.charAt(i);
        }
        System.out.println(name);
        return name;
    }

    public void chooseproblem(ActionEvent event) {
        FileChooser prob = new FileChooser();
        prob.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File f1 = prob.showOpenDialog(null);
        if (f1 != null) {
            String problem = String.valueOf(f1);
            String name = getname(problem);
            problem_btn.setText(name);

        }
    }
    public void chooseinput(ActionEvent event) {
        FileChooser in = new FileChooser();
        in.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File f2 = in.showOpenDialog(null);
        if (f2 != null) {
            String input = String.valueOf(f2);
            input_btn.setText(getname(input));

        }
    }
    public void chooseoutput(ActionEvent event) {
        FileChooser out = new FileChooser();
        out.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File f3 = out.showOpenDialog(null);
        if (f3 != null) {
            String output = String.valueOf(f3);
            output_btn.setText(getname(output));
        }
    }

    public void setSubmit(ActionEvent event)
    {
        System.out.println(name_field.getText());
        System.out.println(time_field.getText());
        System.out.println(memory_field.getText());
        System.out.println(id_field.getText());
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
