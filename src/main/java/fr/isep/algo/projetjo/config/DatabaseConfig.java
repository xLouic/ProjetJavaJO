package fr.isep.algo.projetjo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:olympics_db.db";

    public static void connect() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS athletes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "country TEXT NOT NULL," +
                    "age INTEGER," +
                    "sex TEXT)");
            // Créer d'autres tables pour les sports, événements, et résultats
        } catch (Exception e) {
            System.out.println("Database connection failure: " + e.getMessage());
        }
    }
}

