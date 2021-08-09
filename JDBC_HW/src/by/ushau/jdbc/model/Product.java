package by.ushau.jdbc.model;

import java.io.Serializable;

public class Product extends Entity {
    private String title;
    private Integer id;
    private Double cost;
    private Integer brand_id;

    public Product(String title, Integer id, Double cost, Integer brand_id) {
        this.title = title;
        this.id = id;
        this.cost = cost;
        this.brand_id = brand_id;
    }

    public Product(String title, Integer id, Double cost) {
        this.title = title;
        this.id = id;
        this.cost = cost;
        this.brand_id = 0;
    }

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", cost=" + cost +
                ", brand_id=" + brand_id +
                '}';
    }
}
