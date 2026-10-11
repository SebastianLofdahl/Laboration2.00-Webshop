package se.iths.sebastian.webshop.model;

public class TenPercentDiscount extends Discount {


    public TenPercentDiscount(String description) {
        super(description);
    }

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice <= 0) {
            throw new IllegalArgumentException("Price must be bigger than 0kr");
        }
        double discountedPrice = originalPrice * 0.9;

        return Math.round(originalPrice * 100.0) / 100.0;
    }
}
