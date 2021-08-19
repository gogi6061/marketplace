package com.amiriskhakov.technokratia.controllers;

import com.amiriskhakov.technokratia.anottations.ValidEmail;
import com.amiriskhakov.technokratia.payload.request.OrderRequest;
import com.amiriskhakov.technokratia.payload.response.MessageResponse;
import com.amiriskhakov.technokratia.services.OrderService;
import com.amiriskhakov.technokratia.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
@PreAuthorize("permitAll()")
public class OrderController {

    @Autowired
    ResponseErrorValidation responseErrorValidation;

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest,
                                              BindingResult bindingResult) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        orderService.saveOrder(orderRequest);
        return ResponseEntity.ok(new MessageResponse("Order created"));

    }

    @GetMapping("/get")
    public ResponseEntity<Object> getOrder(@RequestParam("email") @ValidEmail String email) {

        return new ResponseEntity<>(orderService.getOrdersByEmail(email), HttpStatus.OK);


    }

    @GetMapping("/getByArt")
    public ResponseEntity<Object> getOrderArt(@RequestParam("art") String art) {
        return new ResponseEntity<Object>(orderService.getOrdersByArt(art), HttpStatus.OK);
    }

    @GetMapping("/getByDate")
    public ResponseEntity<Object> getOrderBetweenDates(@RequestParam("from") String from,
                                                       @RequestParam("to") String to) {

        return new ResponseEntity<>(orderService.getOrdersByDateBetween(from, to), HttpStatus.OK);


    }


}
