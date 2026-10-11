package se.iths.sebastian.webshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenPercentDiscountTest {

    //Note to self, deklarera min variabel på klassnivå så att alla testmetoder når den
    private TenPercentDiscount tenPercentPriceDiscount;

    @BeforeEach
    void setUpTest() {
        //Note to self, initsiera innan varje test så jag inte behöver skriva så mycke.
        tenPercentPriceDiscount = new TenPercentDiscount("90% rabatt borde räknas ut");

    }

    @Test
    void shouldCalculateTenPercentTest() {
        //Act: beräkning
        double tenPrice = tenPercentPriceDiscount.calculatePrice(100.0);
        //Assert: jämför 90 med det faktiska värdet som borde bli 90%
        assertEquals(90, tenPrice, "This should be 90% of a 100");
    }

    @Test
    void shouldHandleDecimalRounding() {
        //Act: beräkning
        double result = tenPercentPriceDiscount.calculatePrice(33.33);
        //Assert: jämför med 33.33 för att kontrollera decimaler
        //33.33 * 0.9 = 29.997 -> bör avrundas till 30.00
        assertEquals(30.0, result, "This should round out to two decimales");
    }

    @Test
    void shouldThrowExceptionWhenNegative() {
        //Verifierar att rätt argument kastas vid exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tenPercentPriceDiscount.calculatePrice(-50.0);
        });
        //Verifierar att det är mitt bestämda felmeddelande
        assertTrue(exception.getMessage().contains("Price must be bigger than 0"));

    }

    @Test
    void getDescription() {
    }
}