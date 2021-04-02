package com.guerzoniansus.imdbchatbot.questions;

import com.guerzoniansus.imdbchatbot.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionThree implements Question {

    // Welke acteur/actrice heeft in de meeste films geacteerd die onder het hoogst gewaardeerde genre valt?

    private final String query = "WITH mydata AS( " +
            "SELECT actor.primaryname, COUNT(actor.primaryname) AS appearances " +
            "FROM public.actor, public.acts_in, public.genre " +
            "WHERE actor.personid= acts_in.personid " +
            "AND acts_in.titleid = genre.titleid " +
            "AND genre.genre = 'Documentary' " +
            "GROUP BY actor.primaryname " +
            "ORDER BY appearances DESC), " +
            "actors AS (SELECT max(appearances) AS max_appearances FROM mydata) " +
            "SELECT primaryname, appearances FROM mydata, actors WHERE appearances = max_appearances; ";


    @Override
    public String getAnswer() {
        String actorNames = "";
        int appearance = 0;

        try {
            ResultSet result = Database.executeQuery(query);

            while (result.next()) {
                appearance = result.getInt("appearances");

                if (result.isFirst())
                    actorNames += result.getString("primaryname");
                else if (result.isLast())
                    actorNames += " en " + result.getString("primaryname");
                else
                    actorNames += ", " + result.getString("primaryname");

            }
            return "De acteurs/actrices die het meest in het hoogst gewaardeerde genre (documentary) hebben gespeeld (" + appearance + " keer) zijn : " + actorNames + ".";

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
        }
    }
}
