package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Product;

import java.util.List;

public class ProductFileStorage implements ProducStorage {

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(String articleNumber) {
        return null;
    }
}
