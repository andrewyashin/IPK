package sample.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import sample.Main;
import sample.Tools;
import sample.database.Connection;
import sample.database.Examinations;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by andrew_yashin on 12/6/16.
 */
public class ExaminationsController implements Initializable{
    private final static String TABLE_NAME = "examinations";
    private final static String ID_STUDENT = "id_student";
    private final static String ID_SUBJECT = "id_subject";
    private final static String MARK = "mark";

    private ObservableList<Examinations> examinationsObservableList = FXCollections.observableArrayList();
    private Main main;
    private Connection mConnection;
    private ArrayList<String> markList = new ArrayList<>(
            Arrays.asList("A", "B", "C", "D", "E", "F", "a", "b", "c", "d", "e", "f"));

    private List<String> insertId;
    private List<String> insertValues;

    private String mIdStudentAdd;
    private String mIdSubjectAdd;
    private String mMarkAdd;

    @FXML TableView<Examinations> tableExaminations;
    @FXML TableColumn<Examinations, Integer> idStudentColumn;
    @FXML TableColumn<Examinations, Integer> idSubjectColumn;
    @FXML TableColumn<Examinations, String> markColumn;

    @FXML TextField idStudentAdd;
    @FXML TextField idSubjectAdd;
    @FXML TextField markAdd;
    @FXML TextField idStudentDelete;

    @FXML Button buttonAdd;
    @FXML Button buttonDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mConnection = new Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initColumns();
        init();
        initTextFields();
        initButtons();
    }

    private void filldata(ResultSet resultSet) throws SQLException{
        while (resultSet.next()){
            Examinations item = new Examinations(resultSet.getInt("id_student"),
                    resultSet.getInt("id_subject"), resultSet.getString("mark"));
            examinationsObservableList.add(item);
        }
    }

    private void init(){
        try {
            examinationsObservableList.clear();
            ResultSet resultSet = mConnection.executeDataFromTable(TABLE_NAME);
            filldata(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        insertId = new ArrayList<>();
        insertId.add(ID_STUDENT);
        insertId.add(ID_SUBJECT);
        insertId.add(MARK);

        tableExaminations.setItems(examinationsObservableList);
        tableExaminations.setEditable(true);
    }

    private void initColumns(){
        idStudentColumn.setCellValueFactory(new PropertyValueFactory<Examinations, Integer>("id_student"));
        idSubjectColumn.setCellValueFactory(new PropertyValueFactory<Examinations, Integer>("id_subject"));
        markColumn.setCellValueFactory(new PropertyValueFactory<Examinations, String>("mark"));

        idStudentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        idStudentColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Examinations, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Examinations, Integer> event) {
                        try {
                            mConnection.executeUpdate("examinations", "id_student", "id_student",
                                    Integer.toString(event.getNewValue()), Integer.toString(event.getOldValue()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        idSubjectColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        idSubjectColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Examinations, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Examinations, Integer> event) {
                        try {
                            mConnection.executeUpdate("examinations", "id_subject", "id_student",
                                    Integer.toString(event.getNewValue()), Integer.toString(
                                            ((Examinations) event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_student())
                            );
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        markColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        markColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Examinations, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Examinations, String> event) {
                        try {
                            mConnection.executeUpdate(
                                    "examinations",
                                    "mark",
                                    "id_student",
                                    Tools.addQuotes(event.getNewValue()),
                                    Integer.toString(((Examinations) event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_student()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initTextFields(){
        idStudentAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idStudentAdd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        idSubjectAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idSubjectAdd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        markAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!markList.contains(newValue)){
                    markAdd.setText("");
                }
            }
        });

        idStudentDelete.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idStudentAdd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void initButtons(){
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mIdStudentAdd = idStudentAdd.getText();
                mIdSubjectAdd = idSubjectAdd.getText();
                mMarkAdd = Tools.addQuotes(markAdd.getText().toUpperCase());

                if (!mIdStudentAdd.isEmpty()
                        && !mIdSubjectAdd.isEmpty()
                        && !mMarkAdd.isEmpty())
                {
                    insertValues = new ArrayList<>();

                    insertValues.add(mIdStudentAdd);
                    insertValues.add(mIdSubjectAdd);
                    insertValues.add(mMarkAdd);

                    try {
                        mConnection.executeInsert(TABLE_NAME, insertId, insertValues);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                    init();

                    idSubjectAdd.clear();
                    idStudentAdd.clear();
                    markAdd.clear();

                }
            }
        });

        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mIdStudentAdd = idStudentDelete.getText();

                if (!mIdStudentAdd.isEmpty())
                {
                    try {
                        mConnection.executeDelete(TABLE_NAME, ID_STUDENT, mIdStudentAdd);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                    init();

                    idStudentDelete.clear();

                }
            }
        });
    }

}
