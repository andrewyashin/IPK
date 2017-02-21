package sample.database;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by andrew_yashin on 12/6/16.
 */
public class Examinations {
    private String name_of_group, name, surname, mark, name_of_subject;
    private Integer id_group, id_student, id_subject;

    public Examinations(String name_of_group, String name, String surname, String mark, String name_of_subject, Integer id_group, Integer id_student, Integer id_subject) {
        this.name_of_group = name_of_group;
        this.name = name;
        this.surname = surname;
        this.mark = mark;
        this.name_of_subject = name_of_subject;
        this.id_group = id_group;
        this.id_student = id_student;
        this.id_subject = id_subject;
    }

    public String getName_of_group() {

        return name_of_group;
    }

    public void setName_of_group(String name_of_group) {
        this.name_of_group = name_of_group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName_of_subject() {
        return name_of_subject;
    }

    public void setName_of_subject(String name_of_subject) {
        this.name_of_subject = name_of_subject;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public Integer getId_subject() {
        return id_subject;
    }

    public void setId_subject(Integer id_subject) {
        this.id_subject = id_subject;
    }
}
