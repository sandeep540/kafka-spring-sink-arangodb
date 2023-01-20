package com.plural.kafkasink.service;

import com.plural.kafkasink.entity.Product;
import com.plural.kafkasink.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ArangoService {

    @Autowired
    private ProductRepository repository;

    public ResponseEntity<Iterable<Product>> findAll() {

        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Optional<Product>> findById(String id) {

        return ResponseEntity.ok(repository.findById(id));
    }
}
