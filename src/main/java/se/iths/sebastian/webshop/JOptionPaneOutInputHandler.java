package se.iths.sebastian.webshop;

import javax.swing.*;

public class JOptionPaneOutInputHandler implements OutInputHandler {
    @Override
    public String prompt(String message) {

        while (true) {
            String input = JOptionPane.showInputDialog(message);

            try {
                if (input == null || input.isEmpty()) {
                    throw new IllegalArgumentException("Input kan ej vara tomt.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Vänligen försök igen. " + e.getMessage());
            }
        }
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public String menu() {

        String choice = JOptionPane.showInputDialog(null, "--1.Add product--\n" +
                "--2.List all products--\n" +
                "--3.Show information regarding products--\n" +
                "--4.Exit the webshop--\n" +
                "Choose between 1-4: ");
        if (choice == null) {
            return "4";
        }
        return choice;
    }
}
