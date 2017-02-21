package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Groups {
    private Integer id_group, id_speciality;
    private String name_of_speciality, date_of_ending, name_of_group;

    public Groups(Integer id_group, Integer id_speciality, String name_of_speciality, String date_of_ending, String name_of_group) {
        this.id_group = id_group;
        this.id_speciality = id_speciality;
        this.name_of_speciality = name_of_speciality;
        this.date_of_ending = date_of_ending;
        this.name_of_group = name_of_group;
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

    public String getName_of_speciality() {
        return name_of_speciality;
    }

    public void setName_of_speciality(String name_of_speciality) {
        this.name_of_speciality = name_of_speciality;
    }

    public String getDate_of_ending() {
        return date_of_ending;
    }

    public void setDate_of_ending(String date_of_ending) {
        this.date_of_ending = date_of_ending;
    }

    public String getName_of_group() {
        return name_of_group;
    }

    public void setName_of_group(String name_of_group) {
        this.name_of_group = name_of_group;
    }
}
