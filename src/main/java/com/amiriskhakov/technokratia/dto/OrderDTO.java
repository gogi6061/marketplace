package com.amiriskhakov.technokratia.dto;

public class OrderDTO {
    private int id;

    private String userEmail;

    public OrderDTO(int id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
