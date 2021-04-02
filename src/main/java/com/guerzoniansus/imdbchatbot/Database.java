package com.guerzoniansus.imdbchatbot;

import java.sql.*;

public class Database {

    private static final String DB_URL = "localhost:5432/IMDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "464646";

    private Database() {

    }

    /**
     * Perform an SQL query on the Database
     * @param query The query to execute
     * @return The ResultSet from the query
     * @throws SQLException When a connection could not be made or when the query couldn't execute properly
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet;
        }
    }
}
