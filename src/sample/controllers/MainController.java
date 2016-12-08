package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by andrew_yashin on 12/7/16.
 */
public class MainController implements Initializable {

    @FXML TabPane tabs;
    @FXML Tab examinations;
    @FXML Tab groups;
    @FXML Tab organisations;
    @FXML Tab shedule;
    @FXML Tab specialities;
    @FXML Tab students;
    @FXML Tab subjects;
    @FXML Tab teachers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
