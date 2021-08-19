package com.amiriskhakov.technokratia.entity;

import com.amiriskhakov.technokratia.anottations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
@Scope(scopeName = "prototype")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int orderNumber;

    @Column(nullable = false)
    @ValidEmail
    private String userEmail;

    @JsonFormat(pattern = "yyyy-mm-dd ")
    @Column(updatable = false)
    private LocalDateTime createDate;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Product> productsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public Order() {
    }


    @PrePersist
    void onCreate() {
        this.createDate = LocalDateTime.now();
        this.orderNumber = Math.abs(createDate.hashCode());
    }
}
