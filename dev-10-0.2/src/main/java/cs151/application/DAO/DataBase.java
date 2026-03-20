package cs151.application.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/// DataBase class, for not nothing much yet, doesn't save any data yet

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:flashcards.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }

    // Call this once when app starts
    public static void init() {
        String createDeckTable = """
            CREATE TABLE IF NOT EXISTS decks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT
            );
        """;

        // Later you can add flashcards table too
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
        } catch (Exception e) {
            throw new RuntimeException("DB init failed", e);
        }
    }
}