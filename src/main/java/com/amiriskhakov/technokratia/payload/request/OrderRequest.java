package com.amiriskhakov.technokratia.payload.request;

import com.amiriskhakov.technokratia.anottations.ValidEmail;
import com.amiriskhakov.technokratia.facade.ProductFacade;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequest {

    @ValidEmail
    @NotEmpty
    private String email;
    @NotEmpty
    private List<ProductFacade> articulsList;

    public OrderRequest() {
    }

    public OrderRequest(String email, List<ProductFacade> articulsList) {
        this.email = email;
        this.articulsList = articulsList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductFacade> getArticulsList() {
        return articulsList;
    }

}
