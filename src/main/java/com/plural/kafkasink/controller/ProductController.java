package com.plural.kafkasink.controller;

import com.plural.kafkasink.entity.Product;
import com.plural.kafkasink.service.ArangoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ArangoService service;

    @GetMapping(value = "/products")
    public ResponseEntity<Iterable<Product>> getProducts() {

        return service.findAll();
    }

    @GetMapping(value = "/product/{id}/")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("id") String id) {

        return service.findById(id);
    }
}