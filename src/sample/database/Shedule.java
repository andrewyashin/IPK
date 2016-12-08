package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Shedule {
    private Integer id_shedule, id_subject, id_teacher, id_group;
    private String date_time, type_of_subject;

    public Shedule(Integer id_shedule, Integer id_subject, Integer id_teacher, Integer id_group, String date_time, String type_of_subject) {
        this.id_shedule = id_shedule;
        this.id_subject = id_subject;
        this.id_teacher = id_teacher;
        this.id_group = id_group;
        this.date_time = date_time;
        this.type_of_subject = type_of_subject;
    }

    public Integer getId_shedule() {
        return id_shedule;
    }

    public void setId_shedule(Integer id_shedule) {
        this.id_shedule = id_shedule;
    }

    public Integer getId_subject() {
        return id_subject;
    }

    public void setId_subject(Integer id_subject) {
        this.id_subject = id_subject;
    }

    public Integer getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Integer id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getType_of_subject() {
        return type_of_subject;
    }

    public void setType_of_subject(String type_of_subject) {
        this.type_of_subject = type_of_subject;
    }
}
