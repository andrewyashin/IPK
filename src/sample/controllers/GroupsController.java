package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    private ObservableList<Groups> examinationsObservableList = FXCollections.observableArrayList();
    private Main main;
    private Connection mConnection;

    private List<String> insertId;
    private List<String> insertValues;

    private String mIdGroupAdd;
    private String mIdSpeciality;
    private String mDateAdd;
    private String mNameAdd;

    @FXML TableView<Groups> tableGroups;
    @FXML TableColumn<Groups, Integer> idGroupColumn;
    @FXML TableColumn<Groups, Integer> idSpecialityColumn;
    @FXML TableColumn<Groups, String> dateColumn;
    @FXML TableColumn<Groups, String> nameColumn;

    @FXML TextField idGroupAdd;
    @FXML TextField idSpecAdd;
    @FXML TextField dateEndAdd;
    @FXML TextField nameAdd;
    @FXML TextField idGroupDelete;

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

    private void filldata(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Groups item = new Groups(resultSet.getInt("id_group"),
                    resultSet.getInt("id_speciality"), resultSet.getString("name_of_group"), resultSet.getDate("date_of_ending").toString());
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
        insertId.add(ID_GROUP);
        insertId.add(ID_SPECIALITY);
        insertId.add(NAME);
        insertId.add(DATE_OF_ENDING);

        tableGroups.setItems(examinationsObservableList);
        tableGroups.setEditable(true);
    }

    private void initColumns(){
        idGroupColumn.setCellValueFactory(new PropertyValueFactory<Groups, Integer>("id_group"));
        idSpecialityColumn.setCellValueFactory(new PropertyValueFactory<Groups, Integer>("id_speciality"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Groups, String>("name_of_group"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Groups, String>("date_of_ending"));

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
                new EventHandler<TableColumn.CellEditEvent<Groups, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Groups, Integer> event) {
                        try {
                            mConnection.executeUpdate(TABLE_NAME, "id_group", "id_group",
                                    Integer.toString(event.getNewValue()), Integer.toString(event.getOldValue()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        idSpecialityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        idSpecialityColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Groups, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Groups, Integer> event) {
                        try {
                            mConnection.executeUpdate(TABLE_NAME, "id_speciality", "id_group",
                                    Integer.toString(event.getNewValue()), Integer.toString(
                                            ((Groups) event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_group())
                            );
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Groups, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Groups, String> event) {
                        try {
                            mConnection.executeUpdate(
                                    TABLE_NAME,
                                    "name",
                                    "id_group",
                                    Tools.addQuotes(event.getNewValue()),
                                    Integer.toString(((Groups) event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_group()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });

        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Groups, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Groups, String> event) {
                        try {
                            mConnection.executeUpdate(
                                    TABLE_NAME,
                                    "date_of_ending",
                                    "id_group",
                                    Tools.addQuotes(event.getNewValue()),
                                    Integer.toString(((Groups) event.getTableView().getItems().get(event.getTablePosition().getRow())).getId_group()));
                            init();
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initTextFields(){
        idGroupAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idGroupAdd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        idSpecAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idSpecAdd.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        nameAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

        dateEndAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

        idGroupDelete.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idGroupDelete.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }

    private void initButtons(){
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mIdGroupAdd = idGroupAdd.getText();
                mIdSpeciality = idSpecAdd.getText();
                mNameAdd = Tools.addQuotes(nameAdd.getText());
                mDateAdd = Tools.addQuotes(dateEndAdd.getText());

                if (!mIdGroupAdd.isEmpty()
                        && !mIdSpeciality.isEmpty()
                        && !mNameAdd.isEmpty()
                        && !mDateAdd.isEmpty())
                {
                    insertValues = new ArrayList<>();

                    insertValues.add(mIdGroupAdd);
                    insertValues.add(mIdSpeciality);
                    insertValues.add(mNameAdd);
                    insertValues.add(mDateAdd);

                    try {
                        mConnection.executeInsert(TABLE_NAME, insertId, insertValues);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                    init();

                    idGroupAdd.clear();
                    idSpecAdd.clear();
                    nameAdd.clear();
                    dateEndAdd.clear();

                }
            }
        });

        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
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
}
