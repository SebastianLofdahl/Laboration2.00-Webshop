package se.iths.sebastian.webshop;

public class Main {
    static void main() {

        ProductFileStorage newfile = new ProductFileStorage();
        WebShopManager newInstance = new WebShopManager(newfile);
        newInstance.startWebshop();
    }
}
