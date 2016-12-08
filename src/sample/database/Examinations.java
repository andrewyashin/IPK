package sample.database;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by andrew_yashin on 12/6/16.
 */
public class Examinations {
    private Integer id_student, id_subject;
    private String mark;

    public Examinations(int id_student, int id_subject, String mark) {
        this.id_student = (id_student);
        this.id_subject = (id_subject);
        this.mark = (mark);
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
