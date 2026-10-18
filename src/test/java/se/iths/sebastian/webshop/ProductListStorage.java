package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Product;

import java.util.ArrayList;
import java.util.List;

/*Hanterar lagringen av webbshoppens produkter i minnet med hjälp av en lista.
 Implementerar gränssnittet ProductStorage.*/

public class ProductListStorage implements ProductStorage {

    // En intern lista som sparar alla produkter i minnet under testest körning
    private List<Product> testProducts = new ArrayList<>();

    //Sparar en ny produkt i lagringen genom att lägga till den i listan.
    @Override
    public void saveProduct(Product product) {
        testProducts.add(product);
    }

    //Hämtar och returnerar hela listan med alla sparade produkter.
    @Override
    public List<Product> getProducts() {
        return testProducts;
    }

    /**
     * Söker efter en specifik produkt baserat på dess artikelnummer.
     * Sökningen ignorerar om det är stora eller små bokstäver (case-insensitive).
     *
     * @return Produkten om den hittas, annars null.
     */
    @Override
    public Product getProduct(String articleNumber) {

        for (Product product : testProducts) {
            if (product.getArticleNumber().equalsIgnoreCase(articleNumber)) {
                return product;
            }
        }
        return null;
    }
}
