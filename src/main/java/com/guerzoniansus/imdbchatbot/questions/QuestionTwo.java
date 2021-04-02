package com.guerzoniansus.imdbchatbot.questions;

import com.guerzoniansus.imdbchatbot.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTwo implements Question {
    private final String query = "WITH mydata AS( " +
            "SELECT actor.personid, actor.primaryname, count(*) AS appearances " +
            "FROM acts_in, actor, title " +
            "WHERE acts_in.personid = actor.personid " +
            "AND acts_in.titleid = title.titleid " +
            "AND title.rating >= 9 " +
            "GROUP BY actor.personid), " +
            "themax AS (SELECT max(appearances) AS max_appearances FROM mydata) " +
            "SELECT * FROM mydata, themax WHERE appearances = max_appearances;";

    @Override
    public String getAnswer() {
        String actorNames = "";
        int appearance = 0;

        try {
            ResultSet result = Database.executeQuery(query);

            while (result.next()) {
                if(result.isFirst())
                    actorNames += result.getString("primaryname");
                else if(result.isLast())
                    actorNames += " en " + result.getString("primaryname");
                else
                    actorNames += ", " + result.getString("primaryname");

                    appearance = result.getInt("appearances");

            }
            return "De acteurs/actrices " + actorNames + " hebben " + appearance + " keer  in een film gespeeld met een rating van 9 of hoger.";

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return "Error: Er is iets fout gegaan, de data kon niet uit de database gehaald worden.";
        }
    }
}
