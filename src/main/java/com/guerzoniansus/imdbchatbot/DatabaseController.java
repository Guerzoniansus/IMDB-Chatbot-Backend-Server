package com.guerzoniansus.imdbchatbot;

import com.guerzoniansus.imdbchatbot.questions.QuestionOne;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class DatabaseController {

    @RequestMapping("/")
    public String handleDatabaseReuqests(@RequestParam String question) {
        switch (question) {
            case "1":
                return new QuestionOne().getAnswer();
            default:
                return "Deze vraag wordt niet herkend";
        }

    }


}
