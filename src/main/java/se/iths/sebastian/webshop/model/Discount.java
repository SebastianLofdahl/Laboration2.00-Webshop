package se.iths.sebastian.webshop.model;

public class Discount {

    protected String description;

    public Discount(String description) {
        this.description = description;
    }

    public abstract double calculatePrice(double originalPrice) {
        
    }

    public String getDescription() {
        return description;
    }
}
