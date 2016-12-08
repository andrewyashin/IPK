package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Specialities {
    private Integer id_speciality, count;
    private  Double price;
    private String name;
    private Boolean is_hostel;

    public Specialities(Integer id_speciality, Integer count, Double price, String name, Boolean is_hostel) {
        this.id_speciality = id_speciality;
        this.count = count;
        this.price = price;
        this.name = name;
        this.is_hostel = is_hostel;
    }

    public Integer getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(Integer id_speciality) {
        this.id_speciality = id_speciality;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_hostel() {
        return is_hostel;
    }

    public void setIs_hostel(Boolean is_hostel) {
        this.is_hostel = is_hostel;
    }
}
