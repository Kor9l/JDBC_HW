package by.ushau.jdbc.model;

import java.io.Serializable;

public class Brand extends Entity {
    private String name;
    private Integer id;

    public Brand() {
    }

    ;

    public Brand(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
