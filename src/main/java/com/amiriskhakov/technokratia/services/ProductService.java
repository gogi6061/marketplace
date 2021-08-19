package com.amiriskhakov.technokratia.services;

import com.amiriskhakov.technokratia.entity.Product;
import com.amiriskhakov.technokratia.exceptions.NoSuchProductException;
import com.amiriskhakov.technokratia.exceptions.ProductException;
import com.amiriskhakov.technokratia.payload.request.ProductRequest;
import com.amiriskhakov.technokratia.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductByArticul(String articul) {
        return productRepository.findByArticul(articul)
                .orElseThrow(() -> new NoSuchProductException("cant find product"));

    }

    public void saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setCost(productRequest.getCost());
        product.setName(productRequest.getName());
        product.setArticul(productRequest.getArticul());

        try {
            LOG.info("Saving product with articul {}", productRequest.getArticul());
            productRepository.save(product);


        } catch (Exception e) {
            LOG.error("Error during saving {}", e.getMessage());
            throw new ProductException("exception");
        }


    }
}
