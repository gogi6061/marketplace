package com.amiriskhakov.technokratia.services;

import com.amiriskhakov.technokratia.entity.Order;
import com.amiriskhakov.technokratia.entity.Product;
import com.amiriskhakov.technokratia.exceptions.NoSuchProductException;
import com.amiriskhakov.technokratia.exceptions.OrderException;
import com.amiriskhakov.technokratia.facade.ProductFacade;
import com.amiriskhakov.technokratia.payload.request.OrderRequest;
import com.amiriskhakov.technokratia.repository.OrderRepository;
import com.amiriskhakov.technokratia.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order saveOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserEmail(orderRequest.getEmail());
        List<Product> products = new ArrayList<>();
        for (ProductFacade s : orderRequest.getArticulsList()) {
            Product product = productRepository.findByArticul(s.getArticul()).orElseThrow(() ->
                    new NoSuchProductException("not find product "));
            products.add(product);

        }
        order.setProductsList(products);


        try {

            LOG.info("Saving order for {}", orderRequest.getEmail());
            return orderRepository.save(order);


        } catch (Exception e) {
            LOG.error("Error during saving {}", e.getMessage());
            throw new OrderException("exception");
        }

    }

    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findOrdersByUserEmail(email).orElseThrow();

    }


    public List<Order> getOrdersByArt(String art) {
        Product product = productRepository.findByArticul(art).orElseThrow();
        return orderRepository.getAllBy().orElseThrow()
                .stream()
                .filter(u -> u.getProductsList().contains(product))
                .collect(Collectors.toList());

    }

    public List<Order> getOrdersByDateBetween(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00:00");


        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);

        LocalDateTime dateTimeFrom = dateFrom.atTime(0, 0);
        LocalDateTime dateTimeTo = dateTo.atTime(0, 0);

        System.out.println(dateFrom);
        return orderRepository.findOrdersByCreateDateBetween(dateTimeFrom, dateTimeTo).orElseThrow();
    }

}
