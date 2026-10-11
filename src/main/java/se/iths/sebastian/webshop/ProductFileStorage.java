package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements ProductStorage {

    private Path file = Path.of("products.txt");

    @Override
    public void saveProduct(Product product) {

        try {
            Files.writeString(file, product.toFileLine() + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            //Istället för e.getMessage() nu följer den hela loppet och förklarar mer detaljerat
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        if (!Files.exists(file)) {
            return products;
        }

        try {
            List<String> fileLines = Files.readAllLines(file);
            for (String fileLine : fileLines) {
                Product inFile = Product.fromFileLine(fileLine);
                products.add(inFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getProduct(String articleNumber) {
        List<Product> products = getProducts();

        for (Product exactProduct : products) {
            if (exactProduct.getArticleNumber().equalsIgnoreCase(articleNumber)) {
                return exactProduct;
            }
        }
        return null;
    }
}
