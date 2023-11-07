package com.Vishnu.quizapp.repository;

import com.Vishnu.quizapp.models.Question;
import com.Vishnu.quizapp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
