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
        //Få fram 10% avdrag
        double discountedPrice = originalPrice * 0.9;
        //Runda av till 2 decimaler
        return Math.round(discountedPrice * 100.0) / 100.0;
    }
}
