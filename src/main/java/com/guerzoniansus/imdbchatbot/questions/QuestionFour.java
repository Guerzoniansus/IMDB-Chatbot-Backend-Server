package com.guerzoniansus.imdbchatbot.questions;

import com.guerzoniansus.imdbchatbot.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionFour implements Question{

    // Welk filmgenre heeft gemiddeld het hoogste budget?

    private final String query = "SELECT genre.genre, AVG(title.cost) AS average_costs " +
            "FROM title, genre " +
            "WHERE title.titleid = genre.titleid " +
            "GROUP BY genre " +
            "ORDER BY average_costs DESC " +
            "LIMIT 1; ";
    @Override
    public String getAnswer() {
        try {
            ResultSet result = Database.executeQuery(query);

            while (result.next()) {
                String genre = result.getString("genre");
                int budget = result.getInt("average_costs");

                return "Het filmgenre " + genre.toLowerCase() + " heeft gemiddeld het hoogste budget en dat budget is: $" + budget + ".";
            }

        }

        catch(SQLException e) {
            System.out.print(e.getMessage());
            return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
        }

        return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
    }
}
