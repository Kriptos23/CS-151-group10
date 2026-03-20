package cs151.application.controller;

import cs151.application.Deck;
import cs151.application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DefineDeckController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descField;

    //doesn't work yet
    public void saveDeck() {
        Deck deck = new Deck(
                1,
                nameField.getText(),
                descField.getText()
        );

        System.out.println("Deck Created: " + deck.getName());
    }

    //goes back to home page
    public void goBack() throws Exception {
        Main.showHomePage();
    }
}