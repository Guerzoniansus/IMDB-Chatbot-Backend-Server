package com.guerzoniansus.imdbchatbot.questions;

import com.guerzoniansus.imdbchatbot.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionOne implements Question {

    // Welk filmgenre heeft gemiddeld de hoogste rating?

    private final String query = "SELECT genre, AVG(title.rating) AS average_rating " +
            "FROM public.genre, public.title " +
            "WHERE genre.titleid = title.titleid " +
            "GROUP BY genre " +
            "ORDER BY average_rating DESC " +
            "LIMIT 1;";

    @Override
    public String getAnswer() {
        try {
            ResultSet result = Database.executeQuery(query);

            while (result.next()) {
                String genre = result.getString("genre");
                int rating = result.getInt("average_rating");

                return "Het hoogst gewaardeerde genre is " + genre.toLowerCase() + " en heeft een rating van " + rating + ".";
            }

        }

        catch(SQLException e) {
            System.out.print(e.getMessage());
            return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
        }

        return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
    }
}
