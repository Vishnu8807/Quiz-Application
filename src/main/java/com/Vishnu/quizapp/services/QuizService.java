package com.Vishnu.quizapp.services;

import com.Vishnu.quizapp.models.Question;
import com.Vishnu.quizapp.models.QuestionDto;
import com.Vishnu.quizapp.models.Quiz;
import com.Vishnu.quizapp.models.Response;
import com.Vishnu.quizapp.repository.QuestionRepository;
import com.Vishnu.quizapp.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    QuizRepository quizRepository;
    QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<String> createQuiz(String category,int numQ, String title) {
        Quiz quiz = new Quiz();

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestion(Integer id) {
        List<QuestionDto> questionsForUser = new ArrayList<>();
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        for (Question q : questionsFromDb) {
            QuestionDto questionDto = new QuestionDto(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(questionDto);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses) {
            if(response.getResponse().equals(questionsFromDb.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
