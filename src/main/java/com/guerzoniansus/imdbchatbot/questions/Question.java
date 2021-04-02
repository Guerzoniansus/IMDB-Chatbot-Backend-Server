package com.guerzoniansus.imdbchatbot.questions;

import java.sql.*;

public interface Question {

    /**
     * Get the output of this question
     * @return
     */
    String getAnswer();

}
