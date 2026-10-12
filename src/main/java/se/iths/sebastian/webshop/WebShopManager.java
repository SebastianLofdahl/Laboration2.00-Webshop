package se.iths.sebastian.webshop;

import se.iths.sebastian.webshop.model.Discount;
import se.iths.sebastian.webshop.model.HalfPriceDiscount;
import se.iths.sebastian.webshop.model.Product;
import se.iths.sebastian.webshop.model.TenPercentDiscount;

import java.util.List;

public class WebShopManager {

    private ProductStorage productStorage;

    public WebShopManager(ProductStorage productStorage) {
        this.productStorage = productStorage;
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

    void startWebshop() {

        IO.println("--Please type in your discount code--");
        String discountCode = IO.readln();
        Discount discount = null;

        if (discountCode.equalsIgnoreCase("Halfprice")) {
            discount = new HalfPriceDiscount(discountCode);
            IO.println("You have 50% off!");
        } else {
            discount = new TenPercentDiscount(discountCode);
            IO.println("Store wide sale for 10% off!");
        }

        boolean inMenu = true;

        while (inMenu) {
            IO.println("--1.Add product--");
            IO.println("--2.List all products--");
            IO.println("--3.Show information regarding products--");
            IO.println("--4.Exit the webshop--");
            String choice = IO.readln("Choose between 1-4: ");

            switch (choice) {

                case "1" -> {
                    double price = 0;
                    IO.println("What product would you like to add?\nPlease enter an \"article number\"," +
                            " a \"title\", a \"price\" and a \"description\": ");
                    String articleNumber = IO.readln("Article Number: ");
                    String title = IO.readln("Title: ");
                    try {
                        String inputPrice = IO.readln("Price: ");
                        price = Double.parseDouble(inputPrice);
                    } catch (NumberFormatException e) {
                        boolean giveCorrectPriceNoob = true;

                        while (giveCorrectPriceNoob) {
                            String rightFormat = IO.readln("ERROR!\n You need to enter a price in a correct format! " +
                                    "Please try again, it should be only numbers and a \".\" not \",\"" +
                                    "\n Please enter it in the correct format: ");
                            try {
                                price = Double.parseDouble(rightFormat);
                                giveCorrectPriceNoob = false;
                            } catch (NumberFormatException p) {
                                IO.println("Still wrong format, please try again.");
                            }
                        }
                    }

                    String description = IO.readln("Description: ");
                    String check = IO.readln("You have entered " + articleNumber + "\n" + title + "\n" + price + "\n" +
                            description + "\n" + "Is this correct? Yes/No: ");
                    if (check.equalsIgnoreCase("Yes")) {
                        Product product = new Product(articleNumber, title, price, description);
                        saveProductToStorage(product);
                        IO.println("Product is added");
                    } else {
                        IO.println("Product was not saved, please chose option one to try again.");
                    }
                }
                case "2" -> {
                    List<Product> products = getProductsFromStorage();

                    if (products.isEmpty()) {
                        IO.println("No products found.");
                    } else {
                        IO.println("--Product list--");

                        for (Product product : products) {
                            double discountedPrice = discount.calculatePrice(product.getPrice());

                            IO.println("\nArticle number: " + product.getArticleNumber()
                                    + "\nTitle: " + product.getTitle()
                                    + "\nOrdinary price: " + product.getPrice()
                                    + "\nDiscounted price: " + discountedPrice
                                    + "\nDescription: " + product.getDescription());
                        }
                    }

                }
                case "3" -> {

                    String articleNumber = IO.readln("--Enter an article number of a product--\n");
                    Product product = getProductFromStorage(articleNumber);


                    if (product != null) {
                        double discountedPrice = discount.calculatePrice(product.getPrice());

                        IO.println("\n--Product information--");
                        IO.println("\nArticle number: " + product.getArticleNumber()
                                + "\nTitle: " + product.getTitle()
                                + "\nOriginal price: " + product.getPrice()
                                + "\nDiscounted price: " + discountedPrice
                                + "\nDescription: " + product.getDescription());
                    } else {
                        IO.println("ERROR!\nNo product found with that article number.");
                    }

                }
                case "4" -> {
                    inMenu = false;
                }
            }
        }
        IO.println("Thank you for visiting the webshop, hope to see you again soon!");
        //pseudokod
        //rabattkod = outInputHandler.prompt("Ange rabattkod:")
        //val = outInputHandler.menu()
        //om val är "1":
        //articleNumber = outInputHandler.prompt("Ange artikelnummer:")
        //title = outInputHandler.prompt("Ange titel:")
    }
}
