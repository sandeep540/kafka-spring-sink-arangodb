package com.plural.kafkasink.entity;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

@Document("product")
public class Product {

    @Id // db document field: _key
    private String id;

    @ArangoId // db document field: _id
    private String arangoId;

    private String name;
    private String brand;

    public Product() {
        super();
    }

    public Product(final String id, final String name, final String brand) {
        super();
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
