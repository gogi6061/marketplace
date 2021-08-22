package com.amiriskhakov.technokratia.controllers;

import com.amiriskhakov.technokratia.entity.Order;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<List<Order>> getOrder(@RequestParam("email") String email) {

        return new ResponseEntity<>(orderService.getOrdersByEmail(email), HttpStatus.OK);


    }

    @GetMapping("/getByArt")
    public ResponseEntity<List<Order>> getOrderArt(@RequestParam("art") String art) {
        return new ResponseEntity<>(orderService.getOrdersByArt(art), HttpStatus.OK);
    }

    @GetMapping("/getByDateBetween")
    public ResponseEntity<List<Order>> getOrderBetweenDates(@RequestParam("from") String from,
                                                       @RequestParam("to") String to) {


        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);

        LocalDateTime dateTimeFrom = dateFrom.atTime(0, 0);
        LocalDateTime dateTimeTo = dateTo.atTime(0, 0);

        return new ResponseEntity<>(orderService.getOrdersByDateBetween(dateTimeFrom
                , dateTimeTo), HttpStatus.OK);


    }


}
