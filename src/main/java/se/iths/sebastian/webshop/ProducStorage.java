package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Product;

import java.util.List;

public interface ProducStorage {

    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(String articleNumber);
}
