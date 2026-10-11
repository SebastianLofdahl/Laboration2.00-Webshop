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
        double discountedPrice = originalPrice * 0.5;

        return Math.round(originalPrice * 100.0) / 100.0;
    }
}
