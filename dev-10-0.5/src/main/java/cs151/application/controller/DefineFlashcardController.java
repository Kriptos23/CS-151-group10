package cs151.application.controller;

import cs151.application.DAO.DeckDAO;
import cs151.application.DAO.FlashcardDAO;
import cs151.application.Deck;
import cs151.application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.List;


public class DefineFlashcardController {


    @FXML private ComboBox<Deck> deckComboBox;
    @FXML private TextField frontField;
    @FXML private TextArea backArea;


    @FXML
    public void initialize() {
        try {
            List<Deck> decks = new DeckDAO().findAll();
            deckComboBox.setItems(FXCollections.observableArrayList(decks));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleSaveFlashcard() {
        Deck selectedDeck = deckComboBox.getValue();
        String front = frontField.getText() == null ? "" : frontField.getText().trim();
        String back = backArea.getText() == null ? "" : backArea.getText().trim();


        if (selectedDeck == null || front.isEmpty() || back.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }


        FlashcardDAO.insertFlashcard(selectedDeck.getId(), front, back);
        showAlert("Success", "Flashcard saved.");


        deckComboBox.setValue(null);
        frontField.clear();
        backArea.clear();
    }


    @FXML
    private void goBack() {
        try {
            Main.showHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

