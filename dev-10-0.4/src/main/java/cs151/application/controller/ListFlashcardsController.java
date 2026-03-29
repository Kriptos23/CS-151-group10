package cs151.application.controller;

import cs151.application.DAO.FlashcardDAO;
import cs151.application.Flashcard;
import cs151.application.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ListFlashcardsController {


    @FXML private TableView<Flashcard> table;
    @FXML private TableColumn<Flashcard, Integer> idCol;
    @FXML private TableColumn<Flashcard, Integer> deckIdCol;
    @FXML private TableColumn<Flashcard, String> frontCol;
    @FXML private TableColumn<Flashcard, String> backCol;
    @FXML private TableColumn<Flashcard, String> dateCol;


    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        deckIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getDeckId()).asObject());
        frontCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFront()));
        backCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBack()));
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt()));


        table.setItems(FXCollections.observableArrayList(FlashcardDAO.getAllFlashcards()));
    }


    @FXML
    private void goBack() {
        try {
            Main.showHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

