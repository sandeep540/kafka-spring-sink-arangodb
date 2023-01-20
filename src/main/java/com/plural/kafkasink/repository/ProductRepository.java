package com.plural.kafkasink.repository;


import com.arangodb.springframework.repository.ArangoRepository;
import com.plural.kafkasink.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ArangoRepository<Product, String> {

}