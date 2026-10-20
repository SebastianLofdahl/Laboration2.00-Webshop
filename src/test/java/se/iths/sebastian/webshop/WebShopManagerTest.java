package se.iths.sebastian.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.sebastian.webshop.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebShopManagerTest {

    //Initierar tomma variabler så min before kommer åt dom
    ProductListStorage productListStorage;
    WebShopManager webShopManager;
    FakeInputHandler fakeInputHandler;

    @BeforeEach
    void setUpTest() {
        //Skapar innan varje test så jag behöver skriva mindre
        productListStorage = new ProductListStorage();
        fakeInputHandler = new FakeInputHandler();
        webShopManager = new WebShopManager(productListStorage, fakeInputHandler);
    }

    @Test
    void saveProductToStorageTest() {

        // Arrange/Förbered: Skapa en testprodukt
        Product testProduct = new Product("s4242", "Bunch of Bombas", 42.42,
                "A bunch of bomba bombastics");

        // Act/Utför: Spara produkten via din webShopManager
        webShopManager.saveProductToStorage(testProduct);

        // Assert/Verifiera: Kolla att produkten faktiskt hamnade i lagringen
        Product foundProduct = productListStorage.getProduct("s4242");
        assertNotNull(foundProduct, "Product should be found here");
        assertEquals("Bunch of Bombas", foundProduct.getTitle());
    }

    @Test
    void getProductsFromStorageTest() {

        // Arrange: Lägg till två produkter i lagringen
        Product testProduct1 = new Product("t4242", "Bomba Two: Electric Boogaloo",
                43.43, "Bomba on the loose");
        Product testProduct2 = new Product("y4242", "Bomba Three: Rise of Pink Bomba",
                44.44, "Big Bomba Marc GM reforming Fractal");
        webShopManager.saveProductToStorage(testProduct1);
        webShopManager.saveProductToStorage(testProduct2);

        // Act: Hämta alla produkter via din manager
        List<Product> products = webShopManager.getProductsFromStorage();

        // Assert: Kolla att listan innehåller rätt antal produkter
        assertEquals(2, products.size());
        assertTrue(products.contains(testProduct1));
        assertTrue(products.contains(testProduct2));
    }

    @Test
    void getProductFromStorageTest() {

        // Arrange: Lägg till en produkt direkt i lagringen
        Product testProduct3 = new Product("io4242", "Bomba: Rise of the Bomba",
                48.48, "This is just a test");
        webShopManager.saveProductToStorage(testProduct3);

        // Act: Hämta produkten via dess artikelnummer genom managern
        Product found = webShopManager.getProductFromStorage("io4242");

        // Assert: Verifiera att vi fick tillbaka rätt produkt och att den inte är null
        assertNotNull(found);
        assertEquals("io4242", found.getArticleNumber());
    }

    @Test
    void getProductFromStorageWhenMissingTest() {

        // Act: Ber webShop hämta en produkt med ett artikelnummer som inte finns
        Product noneProduct = webShopManager.getProductFromStorage("nullProduct");

        // Assert: Kollar att vi får tillbaka null (produkten saknas)
        assertNull(noneProduct);
    }

    @Test
    void startWebshop() {

        // Act: Kör igång webbshoppen (använder fakeInputHandler med förberedda svar)
        webShopManager.startWebshop();

        // Assert: Verifiera att en produkt skapades och sparades i lagringen automatiskt
        assertEquals(1, productListStorage.getProducts().size());
        assertEquals("x4242", productListStorage.getProduct("x4242")
                .getArticleNumber());
    }
}