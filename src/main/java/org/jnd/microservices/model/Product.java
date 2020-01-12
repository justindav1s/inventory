package org.jnd.microservices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by justin on 13/10/2015.
 */

public class Product {

    private String id;
    private String name = null;
    private ProductType type = null;
    private Float price = null;
    private int basketIndex = 0;

    private Product() {}

    public Product(String id, String name, ProductType type, Float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getBasketIndex() {
        return basketIndex;
    }

    public void setBasketIndex(int basketIndex) {
        this.basketIndex = basketIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Float getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setPrice(Float price) {

        this.price = price;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 7). // two randomly chosen prime numbers
                append(name).
                append(id).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))
            return false;
        if (obj == this)
            return true;

        Product rhs = (Product) obj;
        return new EqualsBuilder().
                append(name, rhs.name).
                append(id, rhs.id).
                isEquals();
    }

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
