package com.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.web.bind.annotation.*;

/*import com.springsecurity.dto.AuthRequest;*/
import com.springsecurity.dto.Product;
/*import com.springsecurity.entity.UserInfo;*/
/*import com.springsecurity.service.JwtService;*/
import com.springsecurity.service.ProductService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
  /*  @Autowired
    private JwtService jwtService;*/

   /* @Autowired
    private AuthenticationManager authenticationManager;*/

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    /*@PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }*/
    @GetMapping("/data/{city}")
    public List<Object> getWeatherData(){
        Object[] weather = restTemplate.getForObject("http://Weather/weather/data/{city}", Object[].class);
        return Arrays.asList(weather);
    }

    @GetMapping("/all")
    /*@PreAuthorize("hasAuthority('ROLE_ADMIN')")*/
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    /*@PreAuthorize("hasAuthority('ROLE_USER')")*/
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


   /* @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }*/
}