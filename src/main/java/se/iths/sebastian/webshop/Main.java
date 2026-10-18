package se.iths.sebastian.webshop;

public class Main {
    static void main() {

        ProductFileStorage newfile = new ProductFileStorage();
        ConsoleOutInputHandler newInput = new ConsoleOutInputHandler();
        WebShopManager newInstance = new WebShopManager(newfile, newInput);
        newInstance.startWebshop();
    }
}
