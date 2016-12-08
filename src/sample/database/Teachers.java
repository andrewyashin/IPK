package sample.database;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Teachers {
    private Integer id_teacher;
    private String name;
    private Boolean is_degree;

    public Teachers(Integer id_teacher, String name, Boolean is_degree) {
        this.id_teacher = id_teacher;
        this.name = name;
        this.is_degree = is_degree;
    }

    public Integer getId_teacher() {

        return id_teacher;
    }

    public void setId_teacher(Integer id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_degree() {
        return is_degree;
    }

    public void setIs_degree(Boolean is_degree) {
        this.is_degree = is_degree;
    }
}
