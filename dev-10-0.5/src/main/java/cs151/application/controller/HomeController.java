package cs151.application.controller;

import cs151.application.Deck;
import cs151.application.Main;
import cs151.application.DAO.DeckDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HomeController {


    @FXML
    private Label statusLabel;


    @FXML
    private TableView<Deck> deckTable;


    @FXML
    private TableColumn<Deck, Integer> idColumn;


    @FXML
    private TableColumn<Deck, String> nameColumn;


    @FXML
    private TableColumn<Deck, String> descriptionColumn;


    private final DeckDAO deckDAO = new DeckDAO();


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        loadDecks();
    }


    private void loadDecks() {
        try {
            List<Deck> decks = deckDAO.findAll();
            deckTable.setItems(FXCollections.observableArrayList(decks));


            if (decks.isEmpty()) {
                statusLabel.setText("No decks yet. Create your first deck to get started.");
            } else {
                statusLabel.setText("Stored decks:");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Could not load decks.");
        }
    }


    @FXML
    public void goToDefineDeck() {
        try {
            Main.showDefineDeckPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void goToDefineFlashcard() {
        try {
            Main.showDefineFlashcardPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void goToListFlashcards() {
        try {
            Main.showListFlashcardsPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
