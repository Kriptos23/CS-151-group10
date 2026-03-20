package cs151.application;

import cs151.application.DAO.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        //this is data base method, gotta put it before the ui
        DataBase.init();

        showHomePage();//home page is shown first
        stage.show();
    }

    //method to show home page
    public static void showHomePage() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/cs151/application/HomePage.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Flashcards - Home");
    }

    //method to show define deck page
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