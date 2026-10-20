package se.iths.sebastian.webshop;

import com.formdev.flatlaf.FlatDarkLaf;

public class Main {
    static void main() {

//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException |
//                 UnsupportedLookAndFeelException e) {
//        }
        FlatDarkLaf.setup();
        ProductFileStorage newfile = new ProductFileStorage();
        //ConsoleOutInputHandler newInput = new ConsoleOutInputHandler();
        JOptionPaneOutInputHandler newInput = new JOptionPaneOutInputHandler();
        WebShopManager newInstance = new WebShopManager(newfile, newInput);
        newInstance.startWebshop();


    }
}
