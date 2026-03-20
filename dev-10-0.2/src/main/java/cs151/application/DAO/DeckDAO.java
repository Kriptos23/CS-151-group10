package cs151.application.DAO;

import cs151.application.Deck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeckDAO {
    //shold be able to insert a new deck
    public int insert(Deck deck) throws Exception {
        String sql = "INSERT INTO decks(name, description) VALUES(?, ?)";

        try (Connection conn = DataBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, deck.getName().trim());
            ps.setString(2, deck.getDesc());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    //should be able to find decks
    public List<Deck> findAll() throws Exception {
        String sql = "SELECT id, name, description FROM decks ORDER BY name";
        List<Deck> decks = new ArrayList<>();

        try (Connection conn = DataBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                decks.add(new Deck(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        }
        return decks;
    }

    //should be able to delete deck
    public void deleteById(int id) throws Exception {
        String sql = "DELETE FROM decks WHERE id = ?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}