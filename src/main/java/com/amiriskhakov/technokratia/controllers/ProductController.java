package com.amiriskhakov.technokratia.controllers;

import com.amiriskhakov.technokratia.entity.Product;
import com.amiriskhakov.technokratia.payload.request.ProductRequest;
import com.amiriskhakov.technokratia.payload.response.MessageResponse;
import com.amiriskhakov.technokratia.services.ProductService;
import com.amiriskhakov.technokratia.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ResponseErrorValidation responseErrorValidation;

    @Autowired
    ProductService productService;

    @RequestMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest productRequest,
                                             BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;


        productService.saveProduct(productRequest);
        return ResponseEntity.ok(new MessageResponse("Product created"));



    }
}
