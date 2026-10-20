package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Discount;
import se.iths.sebastian.webshop.model.HalfPriceDiscount;
import se.iths.sebastian.webshop.model.Product;
import se.iths.sebastian.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {

    private ProductStorage productStorage;
    private OutInputHandler outInputHandler;

    public WebShopManager(ProductStorage productStorage, OutInputHandler outInputHandler) {
        this.productStorage = productStorage;
        this.outInputHandler = outInputHandler;
    }

    void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    private double readDouble(String promptNum) {
        while (true) {
            String svar = outInputHandler.prompt(promptNum);

            try {
                svar = svar.trim().replace(',', '.');
                return Double.parseDouble(svar);
            } catch (NumberFormatException e) {
                outInputHandler.info("It has to be a number in the correct format, " +
                        "\nPlease try again");
            }
        }
    }

    void startWebshop() {

        String discountCode = outInputHandler.prompt("--Please type in your discount code--\n" +
                "If you do not have a discount code enter \"none\" or any other text to proceed");
        Discount discount = null;

        if (discountCode.equalsIgnoreCase("Halfprice")) {
            discount = new HalfPriceDiscount(discountCode);
            outInputHandler.info("You have 50% off!");
        } else {
            discount = new TenPercentDiscount(discountCode);
            outInputHandler.info("Store wide sale for 10% off!");
        }

        boolean inMenu = true;

        while (inMenu) {
            String choice = outInputHandler.menu();

            switch (choice) {

                case "1" -> {
                    double price = 0;
                    outInputHandler.info("What product would you like to add?\nPlease enter an \"article number\"," +
                            " a \"title\", a \"price\" and a \"description\": ");
                    String articleNumber = outInputHandler.prompt("Article Number: ");
                    String title = outInputHandler.prompt("Title: ");
                    price = readDouble("Price: ");
                    String description = outInputHandler.prompt("Description: ");
                    String check = outInputHandler.prompt("You have entered " + articleNumber + "\n" + title + "\n" + price + "\n" +
                            description + "\n" + "Is this correct? Yes/No: ");
                    if (check.equalsIgnoreCase("Yes")) {
                        Product product = new Product(articleNumber, title, price, description);
                        saveProductToStorage(product);
                        outInputHandler.info("Product is added");
                    } else {
                        outInputHandler.info("Product was not saved, please chose option one to try again.");
                    }
                }
                case "2" -> {
                    List<Product> products = getProductsFromStorage();

                    if (products.isEmpty()) {
                        outInputHandler.info("No products found.");
                    } else {
                        //Skapa strängen före loopen (börjar med rubriken)
                        String text = "--Product List--";

                        for (Product product : products) {
                            double discountedPrice = discount.calculatePrice(product.getPrice());

                            text += "\nArticle number: " + product.getArticleNumber()
                                    + "\nTitle: " + product.getTitle()
                                    + "\nOrdinary price: " + product.getPrice()
                                    + "\nDiscounted price: " + discountedPrice
                                    + "\nDescription: " + product.getDescription()
                                    + "\n-------";
                        }
                        outInputHandler.info(text);
                    }

                }
                case "3" -> {

                    String articleNumber = outInputHandler.prompt("--Enter an article number of a product--\n");
                    Product product = getProductFromStorage(articleNumber);


                    if (product != null) {
                        double discountedPrice = discount.calculatePrice(product.getPrice());

                        String text = "--Product information--\n";
                        text += "\nArticle number: " + product.getArticleNumber()
                                + "\nTitle: " + product.getTitle()
                                + "\nOriginal price: " + product.getPrice()
                                + "\nDiscounted price: " + discountedPrice
                                + "\nDescription: " + product.getDescription();
                        outInputHandler.info(text);
                    } else {
                        outInputHandler.info("ERROR!\nNo product found with that article number.");
                    }

                }
                case "4" -> {
                    inMenu = false;
                }
            }
        }
        outInputHandler.info("Thank you for visiting the webshop, hope to see you again soon!");
    }
}
