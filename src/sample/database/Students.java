package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Students {
    private Integer id_student, id_group, id_organisation;
    private String name, surname;

    public Students(Integer id_student, Integer id_group, Integer id_organisation, String name, String surname) {
        this.id_student = id_student;
        this.id_group = id_group;
        this.id_organisation = id_organisation;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId_student() {

        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }

    public Integer getId_organisation() {
        return id_organisation;
    }

    public void setId_organisation(Integer id_organisation) {
        this.id_organisation = id_organisation;
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
}
