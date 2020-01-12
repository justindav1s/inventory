package org.jnd.microservices.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;

/**
 * Created by justin on 13/10/2015.
 */
public class Basket {

    private int id;
    private String userId;

    private ArrayList<Product> products = new ArrayList<Product>();
    private String total = "0.00";

    public Basket(int id) {
        this.id = id;
        products = new ArrayList<Product>();
    }

    public String getTotal() {
        return total;
    }

    public Basket() {
    }

    public void setTotal(float total) {
        String formatted = String.format("%.2f", total);
        this.total = formatted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> items) {
        this.products = items;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 11). // two randomly chosen prime numbers
                append(id).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Basket))
            return false;
        if (obj == this)
            return true;

        Basket rhs = (Basket) obj;
        return new EqualsBuilder().
                append(id, rhs.id).
                isEquals();
    }

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
