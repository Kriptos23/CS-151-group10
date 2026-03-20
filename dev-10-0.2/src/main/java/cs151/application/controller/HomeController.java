package cs151.application.controller;

import cs151.application.Main;
import cs151.application.DAO.DeckDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label statusLabel;

    //for now only shows for count == 0
    @FXML
    public void initialize() {
        try {
            DeckDAO dao = new DeckDAO();
            int count = dao.findAll().size();

            if (count == 0) {
                statusLabel.setText("No decks yet. Create your first deck to get started.");
            } else if (count == 1) {
                statusLabel.setText("You have 1 deck.");
            } else {
                statusLabel.setText("You have " + count + " decks.");
            }
        } catch (Exception e) {
            statusLabel.setText("Unable to load decks.");
            e.printStackTrace();
        }
    }

    //goes to the define deck page
    @FXML
    public void goToDefineDeck() throws Exception {
        Main.showDefineDeckPage();
    }
}