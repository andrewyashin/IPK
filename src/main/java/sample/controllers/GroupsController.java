package sample.controllers;

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
import sample.database.Groups;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class GroupsController implements Initializable{
    private final static String TABLE_NAME = "groups";
    private final static String ID_GROUP = "id_group";
    private final static String DATE_OF_ENDING = "date_of_ending";
    private final static String NAME = "name";
    private final static String ID_SPECIALITY = "id_speciality";

    private ObservableList<TableViewClass> examinationsObservableList = FXCollections.observableArrayList();
    private Connection mConnection;

    private List<String> insertId;
    private List<String> insertValues;

    private String mIdGroupAdd;
    private String mIdSpeciality;
    private String mDateAdd;
    private String mNameAdd;

    @FXML TableView<TableViewClass> tableGroups;
    @FXML TableColumn<TableViewClass, Integer> idGroupColumn;
    @FXML TableColumn<TableViewClass, String> specialityColumn;
    @FXML TableColumn<TableViewClass, String> dateColumn;
    @FXML TableColumn<TableViewClass, String> nameColumn;

    @FXML TextField idGroupAdd;
    @FXML ChoiceBox specialityAdd;
    @FXML DatePicker dateEndAdd;
    @FXML TextField nameAdd;
    @FXML TextField idGroupDelete;

    @FXML Button buttonAdd;
    @FXML Button buttonDelete;


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

    private void filldata(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Groups groupsItem = new Groups(resultSet.getInt("id_group"),
                    resultSet.getInt("id_speciality"), resultSet.getString("name_of_speciality"),
                    resultSet.getString("name_of_group"), resultSet.getDate("date_of_ending").toString());

            TableViewClass item = new TableViewClass(groupsItem.getId_group(), groupsItem.getName_of_group(),
                    groupsItem.getDate_of_ending(), groupsItem.getName_of_speciality());

            examinationsObservableList.add(item);
        }
    }

    private void init(){
        try {
            examinationsObservableList.clear();
            Statement statement = mConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT groups.id_group, groups.id_speciality, groups.name_of_group, groups.date_of_ending, specialities.name_of_speciality " +
                            "FROM groups " +
                            "INNER JOIN specialities ON groups.id_speciality = specialities.id_speciality"
            );

            filldata(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableGroups.setItems(examinationsObservableList);
        tableGroups.setEditable(true);
    }

    private void initColumns(){
        idGroupColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, Integer>("id_group"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("name_of_speciality"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("name_of_group"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<TableViewClass, String>("date_of_ending"));

        idGroupColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        idGroupColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TableViewClass, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TableViewClass, Integer> event) {
                        try {
                            mConnection.executeUpdate(TABLE_NAME, "id_group", "id_group",
                                    Integer.toString(event.getNewValue()), Integer.toString(event.getOldValue()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TableViewClass, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TableViewClass, String> event) {
                        try {
                            mConnection.executeUpdate(
                                    TABLE_NAME,
                                    "name",
                                    "id_group",
                                    Tools.addQuotes(event.getNewValue()),
                                    Integer.toString((event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_group()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });


    }

    private void initTextFields(){

    }

    private void initButtons(){

        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mIdGroupAdd = idGroupDelete.getText();

                if (!mIdGroupAdd.isEmpty())
                {
                    try {
                        mConnection.executeDelete(TABLE_NAME, ID_GROUP, mIdGroupAdd);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                    init();

                    idGroupDelete.clear();

                }
            }
        });
    }

    private class TableViewClass{
        private Integer id_group;
        private String name_of_group, date_of_ending, name_of_speciality;

        public TableViewClass(Integer id_group, String name_of_group, String date_of_ending, String name_of_speciality) {
            this.id_group = id_group;
            this.name_of_group = name_of_group;
            this.date_of_ending = date_of_ending;
            this.name_of_speciality = name_of_speciality;
        }

        public Integer getId_group() {

            return id_group;
        }

        public void setId_group(Integer id_group) {
            this.id_group = id_group;
        }

        public String getName_of_group() {
            return name_of_group;
        }

        public void setName_of_group(String name_of_group) {
            this.name_of_group = name_of_group;
        }

        public String getDate_of_ending() {
            return date_of_ending;
        }

        public void setDate_of_ending(String date_of_ending) {
            this.date_of_ending = date_of_ending;
        }

        public String getName_of_speciality() {
            return name_of_speciality;
        }

        public void setName_of_speciality(String name_of_speciality) {
            this.name_of_speciality = name_of_speciality;
        }
    }
}
