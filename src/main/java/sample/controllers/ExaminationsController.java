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

import javax.tools.Tool;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by andrew_yashin on 12/6/16.
 */
public class ExaminationsController implements Initializable{
    private final static String TABLE_NAME = "examinations";
    private final static String ID_STUDENT = "id_student";
    private final static String ID_SUBJECT = "id_subject";
    private final static String MARK = "mark";

    private List<Examinations> list = new ArrayList<>();

    private ObservableList<TableViewClass> examinationsObservableList = FXCollections.observableArrayList();

    private ObservableList<String> groupList = FXCollections.observableArrayList();
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    private ObservableList<String> surnameList = FXCollections.observableArrayList();
    private ObservableList<String> subjectList = FXCollections.observableArrayList();

    private Connection mConnection;
    private ArrayList<String> markList = new ArrayList<>(
            Arrays.asList("A", "B", "C", "D", "E", "F", "a", "b", "c", "d", "e", "f", ""));

    @FXML TableView<TableViewClass> tableExaminations;
    @FXML TableColumn<TableViewClass, String> groupColumn;
    @FXML TableColumn<TableViewClass, String> surnameColumn;
    @FXML TableColumn<TableViewClass, String> nameColumn;
    @FXML TableColumn<TableViewClass, String> subjectColumn;
    @FXML TableColumn<TableViewClass, String> markColumn;


    @FXML ChoiceBox groupAdd;
    @FXML ChoiceBox surnameAdd;
    @FXML ChoiceBox nameAdd;
    @FXML ChoiceBox subjectAdd;

    @FXML TextField markAdd;

    @FXML Button buttonAdd;

    private int id_group = 0;
    private int id_subject = 0;
    private int id_student = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mConnection = new Connection();

            initColumns();
            init();
            initTextFields();
            initButtons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filldata(ResultSet resultSet) throws SQLException{
        while (resultSet.next()){
            Examinations examinationsItem = new Examinations(resultSet.getString("name_of_group"), resultSet.getString("name"),
                    resultSet.getString("surname"), resultSet.getString("mark"), resultSet.getString("name_of_subject"),
                    resultSet.getInt("id_group"), resultSet.getInt("id_student"), resultSet.getInt("id_subject"));

            TableViewClass item = new TableViewClass(examinationsItem.getName_of_group(), examinationsItem.getSurname(),
                    examinationsItem.getName(), examinationsItem.getName_of_subject(), examinationsItem.getMark());
            list.add(examinationsItem);
            examinationsObservableList.add(item);
        }
    }

    private void init(){
        try {
            groupList.clear();
            examinationsObservableList.clear();
            Statement statement = mConnection.getStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT examinations.id_student, examinations.id_subject, groups.id_group, students.name, students.surname, groups.name_of_group, examinations.mark, subjects.name_of_subject " +
                            "FROM examinations " +
                            "INNER JOIN students ON examinations.id_student = students.id_student " +
                            "INNER JOIN subjects ON examinations.id_subject = subjects.id_subject " +
                            "INNER JOIN groups ON students.id_group = groups.id_group " +
                            "ORDER BY name_of_group"
            );

            Statement statement1 = mConnection.getConnection().createStatement();
            ResultSet groupSet = statement1.executeQuery("SELECT name_of_group FROM groups");
            while (groupSet.next()){
                groupList.add(groupSet.getString("name_of_group"));
            }

            filldata(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }


        tableExaminations.setItems(examinationsObservableList);
        tableExaminations.setEditable(true);
    }

    private void initColumns(){
        groupColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("name_of_group"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("name"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("name_of_subject"));
        markColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("mark"));

        markColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        markColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TableViewClass, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TableViewClass, String> event) {
                int position = event.getTablePosition().getRow();

                int id_subject = list.get(position).getId_subject();
                int id_student = list.get(position).getId_student();
                try {
                    mConnection.getStatement().executeUpdate("UPDATE " + TABLE_NAME +
                            " SET mark = " + Tools.addQuotes(event.getNewValue()) + " WHERE " +
                            ID_STUDENT + " = " + id_student + " AND " + ID_SUBJECT + " = " + id_subject);
                } catch (SQLException e){
                    e.printStackTrace();
                }

                init();
            }
        });
    }

    private void initTextFields(){


        groupAdd.setItems(groupList);
        groupAdd.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            subjectList.clear();
                            surnameList.clear();
                            nameList.clear();


                            Statement statement = mConnection.getConnection().createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT id_group FROM groups WHERE " +
                                    " name_of_group = " + Tools.addQuotes(groupList.get(newValue.intValue())));

                            while (resultSet.next()) {
                                id_group = resultSet.getInt("id_group");
                            }

                            Statement statement1 = mConnection.getConnection().createStatement();
                            resultSet = statement.executeQuery("SELECT surname FROM students WHERE id_group = " + id_group);

                            while (resultSet.next()){
                                surnameList.add(resultSet.getString("surname"));
                            }

                            surnameAdd.setItems(surnameList);
                        } catch (SQLException e){
                            System.out.print(e.getMessage());
                        }
                    }
                });

        surnameAdd.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            nameList.clear();
                            subjectList.clear();

                            Statement statement = mConnection.getConnection().createStatement();

                            String s2 = Tools.addQuotes(surnameList.get(newValue.intValue()));
                            ResultSet resultSet = statement.executeQuery(
                                    "SELECT name FROM students WHERE id_group = " + id_group +
                                            " AND surname = " + s2);

                            while (resultSet.next()){
                                nameList.add(resultSet.getString("name"));
                            }

                            nameAdd.setItems(nameList);
                        } catch (SQLException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
        );

        nameAdd.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            subjectList.clear();

                            Statement statement1 = mConnection.getConnection().createStatement();
                            ResultSet resultSet1 = statement1.executeQuery("SELECT name_of_subject FROM subjects");

                            while (resultSet1.next()){
                                subjectList.add(resultSet1.getString("name_of_subject"));
                            }


                            subjectAdd.setItems(subjectList);
                        } catch (SQLException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
        );

        subjectAdd.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try {
                            Statement statement = mConnection.getConnection().createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT id_student FROM students " +
                                    "WHERE name = " + Tools.addQuotes(nameAdd.getValue().toString()) +
                                    " AND surname = " + Tools.addQuotes(surnameAdd.getValue().toString()) +
                                    " AND id_group = " + id_group);

                            while (resultSet.next()){
                                id_student = resultSet.getInt("id_student");
                            }

                            Statement statement1 = mConnection.getConnection().createStatement();
                            ResultSet resultSet1 = statement1.executeQuery("SELECT id_subject FROM subjects " +
                                    "WHERE name_of_subject = " + Tools.addQuotes(subjectAdd.getValue().toString()));

                            while (resultSet1.next()){
                                id_subject = resultSet1.getInt("id_subject");
                            }

                        } catch (SQLException e) {
                            System.out.print(e.getMessage());
                        }
                    }
                }
        );

        markAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!markList.contains(newValue)){
                    markAdd.setText("");
                }
            }
        });

    }

    private void initButtons(){
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Statement statement = mConnection.getConnection().createStatement();
                    statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " +
                            "(id_subject, id_student, mark) VALUE (" + id_subject + "," + id_student + "," + Tools.addQuotes(markAdd.getText()) + ")");
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }

                init();
            }
        });
    }

    private class TableViewClass{
        private String name_of_group, surname, name, name_of_subject, mark;

        public TableViewClass(String name_of_group, String surname, String name, String name_of_subject, String mark) {
            this.name_of_group = name_of_group;
            this.surname = surname;
            this.name = name;
            this.name_of_subject = name_of_subject;
            this.mark = mark;
        }

        public String getName_of_group() {
            return name_of_group;
        }

        public void setName_of_group(String name_of_group) {
            this.name_of_group = name_of_group;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_of_subject() {
            return name_of_subject;
        }

        public void setName_of_subject(String name_of_subject) {
            this.name_of_subject = name_of_subject;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }
}
