package se.iths.sebastian.webshop;

public class ConsoleOutInputHandler implements OutInputHandler {

    @Override
    public String prompt(String message) {

        while (true) {
            String input = IO.readln(message);

            try {
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Input kan ej vara tomt.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                IO.println("Vänligen försök igen. " + e.getMessage());
            }
        }
    }

    @Override
    public void info(String message) {
        IO.println(message);

    }

    @Override
    public String menu() {
        IO.println("--1.Add product--");
        IO.println("--2.List all products--");
        IO.println("--3.Show information regarding products--");
        IO.println("--4.Exit the webshop--");
        String choice = IO.readln("Choose between 1-4: ");
        return choice;
    }
}

