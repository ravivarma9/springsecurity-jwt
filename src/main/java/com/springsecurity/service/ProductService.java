package com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.springsecurity.dto.Product;
import com.springsecurity.repository.ProductRepository;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Service
public class ProductService {

    List<Product> productList = null;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
    	productList=productRepository.findAll();
    	System.out.println("XXXX"+productList.size());
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}
