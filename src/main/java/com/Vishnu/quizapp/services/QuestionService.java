package com.Vishnu.quizapp.services;


import com.Vishnu.quizapp.models.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    ResponseEntity<List<Question>> getAllQuestions();

    ResponseEntity<List<Question>> getQuestionsByCategory(String category);

    ResponseEntity<String> addQuestion(Question question);
}
