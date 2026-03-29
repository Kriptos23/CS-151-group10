package cs151.application.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {
    private static final String DB_FOLDER = "data";
    private static final String DB_URL = "jdbc:sqlite:" + DB_FOLDER + "/flashcards.db";

    public static Connection getConnection() throws Exception {
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createDeckTable = """
            CREATE TABLE IF NOT EXISTS decks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT
            );
        """;

        String createFlashcardTable = """
            CREATE TABLE IF NOT EXISTS flashcards (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                deck_id INTEGER NOT NULL,
                front_text TEXT NOT NULL,
                back_text TEXT NOT NULL,
                status TEXT NOT NULL,
                creation_date TEXT NOT NULL,
                last_review_date TEXT NOT NULL,
                UNIQUE(deck_id, front_text),
                FOREIGN KEY(deck_id) REFERENCES decks(id) ON DELETE CASCADE
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createDeckTable);
            stmt.execute(createFlashcardTable);
            System.out.println("Database ready.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB init failed", e);
        }
    }
}