package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Organisations {
    private Integer id_speciality;
    private String name, address;

    public Organisations(Integer id_speciality, String name, String address) {
        this.id_speciality = id_speciality;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
