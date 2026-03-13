package cs151.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showHomePage();
        stage.show();
    }

    public static void showHomePage() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/cs151/application/HomePage.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Flashcards - Home");
    }

    public static void showDefineDeckPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/cs151/application/DefineDeck.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Define Deck");
    }

    public static void main(String[] args) {
        launch();
    }
}