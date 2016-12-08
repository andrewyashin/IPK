package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Subjects {
    private Integer id_subject;
    private String name;

    public Subjects(Integer id_subject, String name) {
        this.id_subject = id_subject;
        this.name = name;
    }

    public Integer getId_subject() {

        return id_subject;
    }

    public void setId_subject(Integer id_subject) {
        this.id_subject = id_subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
