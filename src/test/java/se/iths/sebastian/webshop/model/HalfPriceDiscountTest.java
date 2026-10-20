package se.iths.sebastian.webshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HalfPriceDiscountTest {

    //Note to self, deklarera min variabel på klassnivå så att alla testmetoder når den
    private HalfPriceDiscount halfPriceDiscount;

    @BeforeEach
    void setUpTest() {
        //Note to self, initsiera innan varje test så jag inte behöver skriva så mycke.
        halfPriceDiscount = new HalfPriceDiscount("50% should be counted");
    }

    @Test
    void shouldCalculateHalfPriceTest() {
        //Act: beräkning
        double halfPrice = halfPriceDiscount.calculatePrice(100.0);
        //Assert: jämför med 50 mot faktiska värdet, enkelt (100) så bör bli 50
        assertEquals(50, halfPrice, "50kr should be 50% of a 100");

    }

    @Test
    void shouldHandleDecimalRounding() {
        //Act: beräkning
        double result = halfPriceDiscount.calculatePrice(33.33);
        //Assert: jämför med 33.33 för att kontrollera decimaler
        //(33.33 / 2 = 16.665 bör avrundas till 16.67
        //0.001 är delta eller felmarginal så att vi kan avrunda lättare (google som visade mig hur delta funkar)
        assertEquals(16.67, result, 0.001, "Should round to two decimales");
    }

    @Test
    void shouldThrowExceptionWhenNegative() {
        //Lambda för att rätt typ av exeption kastas vid minus
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            halfPriceDiscount.calculatePrice(-50.);
        });
        //Verifierar att det är korrekt felmeddelande
        assertTrue(exception.getMessage().contains("Price must be bigger than 0"));
    }

    @Test
    void shouldGetDescriptionTest() {
        // Act: Hämta beskrivningen från objektet
        String description = halfPriceDiscount.getDescription();

        // Assert: Verifiera att den matchar strängen från setUpTest
        assertEquals("50% should be counted", description,
                "The description should match the constructor argument");
    }
}