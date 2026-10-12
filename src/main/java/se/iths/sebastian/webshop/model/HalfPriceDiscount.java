package se.iths.sebastian.webshop.model;

public class HalfPriceDiscount extends Discount {


    public HalfPriceDiscount(String description) {
        super(description);
    }

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice <= 0) {
            throw new IllegalArgumentException("Price must be bigger than 0kr");
        }
        //Räkna fram 50% avdrag
        double discountedPrice = originalPrice * 0.5;
        //Avrunda till två decimaler
        return Math.round(discountedPrice * 100.0) / 100.0;
    }
}
