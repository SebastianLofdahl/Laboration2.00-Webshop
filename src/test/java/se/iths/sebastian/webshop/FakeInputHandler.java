package se.iths.sebastian.webshop;

import java.util.List;

public class FakeInputHandler implements OutInputHandler {

    // Förberedda svar för när programmet ställer frågor om en produkt (t.ex. artikelnummer, titel)
    private List<String> answers = List.of("halfprice", "x4242", "test title", "42.42", "test description",
            "Yes");
    // En räknare (index) som håller koll på vilket svar i listan 'answers' som står på tur att returneras
    private int next = 0;

    // Förberedda menyval (t.ex. 1 = Lägg till produkt, 4 = Avsluta programmet)
    private List<String> menuChoices = List.of("1", "4");
    // En räknare som håller koll på vilket menyval i listan 'menuChoices' som står på tur
    private int nextMenu = 0;

    //Simulerar en vanlig textfråga till användaren.
    // Returnerar nästa förberedda svar från listan och ökar räknaren med 1.
    @Override
    public String prompt(String message) {
        return answers.get(next++);
    }

    //Simulerar informationsutskrifter i terminalen.
    //Denna lämnas tom eftersom vi inte behöver testa själva utskrifterna i konsolen.
    @Override
    public void info(String message) {
        //Gör inget under testet
    }

    //Simulerar när användaren gör ett val i huvudmenyn.
    //Returnerar nästa förberedda menyval och ökar räknaren med 1.
    @Override
    public String menu() {
        return menuChoices.get(nextMenu++);
    }
}
