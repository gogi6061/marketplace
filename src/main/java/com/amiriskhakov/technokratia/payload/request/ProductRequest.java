package com.amiriskhakov.technokratia.payload.request;

import javax.validation.constraints.NotEmpty;

public class ProductRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private double cost;

    @NotEmpty
    private String articul;

    public ProductRequest(String name, double cost, String articul) {
        this.name = name;
        this.cost = cost;
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }
}
