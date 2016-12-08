package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Groups {
    private Integer id_group, id_speciality;
    private String name, date_of_ending;

    public Groups(Integer id_group, Integer id_speciality, String name, String date_of_ending) {
        this.id_group = id_group;
        this.id_speciality = id_speciality;
        this.name = name;
        this.date_of_ending = date_of_ending;
    }

    public Integer getId_group() {
        return id_group;
    }

    public void setId_group(Integer id_group) {
        this.id_group = id_group;
    }

    public Integer getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(Integer id_speciality) {
        this.id_speciality = id_speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_ending() {
        return date_of_ending;
    }

    public void setDate_of_ending(String date_of_ending) {
        this.date_of_ending = date_of_ending;
    }
}
