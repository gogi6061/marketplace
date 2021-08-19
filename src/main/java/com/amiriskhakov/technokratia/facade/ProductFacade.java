package com.amiriskhakov.technokratia.facade;

public class ProductFacade {
    public String articul;

    public ProductFacade() {
    }

    public ProductFacade(String articul) {
        this.articul = articul;
    }

    public String getArticul() {
        return articul;
    }
}
