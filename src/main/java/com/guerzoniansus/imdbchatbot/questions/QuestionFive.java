package com.guerzoniansus.imdbchatbot.questions;

import com.guerzoniansus.imdbchatbot.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionFive implements Question{
//Welk filmgenre heeft in verhouding het minste budget nodig voor de hoogste rating?

    private final String query = "WITH mydata AS ( " +
            "SELECT genre.genre, AVG(title.cost) AS average_costs, AVG(title.rating) AS average_rating " +
            "FROM title, genre " +
            "WHERE title.titleid = genre.titleid " +
            "GROUP BY genre), " +
            "highest_rating as (SELECT max(average_rating) AS max_rating FROM mydata), " +
            "lowest_cost AS (SELECT min(average_costs) AS min_cost FROM mydata, highest_rating  " +
            "WHERE average_rating = max_rating) " +
            "SELECT genre, max_rating, min_cost " +
            "FROM mydata, highest_rating, lowest_cost " +
            "WHERE average_rating = max_rating; ";
    @Override
    public String getAnswer() {
        try {
            ResultSet result = Database.executeQuery(query);

            while (result.next()) {
                String genre = result.getString("genre");
                int budget = result.getInt("min_cost");
                int rating = result.getInt("max_rating");

                return "Het filmgenre " + genre.toLowerCase() + " heeft gemiddeld een budget nodig van: $" + budget + " nodig voor een rating van "+ rating +".";
            }

        }

        catch(SQLException e) {
            System.out.print(e.getMessage());
            return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
        }

        return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
    }

}
