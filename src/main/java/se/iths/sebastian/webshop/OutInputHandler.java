package se.iths.sebastian.webshop;

public interface OutInputHandler {

    String prompt(String message);

    void info(String message);

    String menu();

    private String readInput(String prompt) {

        while (true) {
            String input = IO.readln(prompt);

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
}
