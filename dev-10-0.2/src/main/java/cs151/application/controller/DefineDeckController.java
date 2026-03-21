package cs151.application.controller;

import cs151.application.Deck;
import cs151.application.Main;
import cs151.application.DAO.DeckDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DefineDeckController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    private final DeckDAO deckDAO = new DeckDAO();

    @FXML
    public void saveDeck() {
        try {
            String name = nameField.getText() == null ? "" : nameField.getText().trim();
            String description = descriptionArea.getText() == null ? "" : descriptionArea.getText().trim();

            if (name.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Deck name cannot be empty.");
                return;
            }

            if (deckDAO.deckNameExists(name)) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Deck name must be unique.");
                return;
            }

            Deck deck = new Deck(name, description);
            deckDAO.insert(deck);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Deck saved successfully.");
            Main.showHomePage();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Could not save deck.");
        }
    }

    @FXML
    public void goBack() {
        try {
            Main.showHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}