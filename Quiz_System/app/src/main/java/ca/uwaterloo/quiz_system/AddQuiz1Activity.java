package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuiz1Activity extends AppCompatActivity {
    EditText editQuestion, editOptionA, editOptionB, editOptionC, editOptionD, editRightAnswer;
    Button buttonSubmitNewQuiz;
    QuizDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new QuizDBHelper(getApplicationContext());
        //db.onUpgrade(db.getWritableDatabase(), 1, 0);
        editQuestion = (EditText) findViewById(R.id.editQuestion);
        editOptionA = (EditText) findViewById(R.id.editOptionA);
        editOptionB = (EditText) findViewById(R.id.editOptionB);
        editOptionC = (EditText) findViewById(R.id.editOptionC);
        editOptionD = (EditText) findViewById(R.id.editOptionD);
        editRightAnswer = (EditText) findViewById(R.id.editRightAnswer);

        buttonSubmitNewQuiz = (Button) findViewById(R.id.buttonSubmitNewQuiz);
        buttonSubmitNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editQuestion.getText().toString();
                String optionA = editOptionA.getText().toString();
                String optionB = editOptionB.getText().toString();
                String optionC = editOptionC.getText().toString();
                String optionD = editOptionD.getText().toString();
                String rightAnswer = editRightAnswer.getText().toString();

                QuizOptionAnswer qoa = new QuizOptionAnswer(question, optionA, optionB, optionC, optionD, rightAnswer);
                db.createQuiz(qoa); //userId has to be unique
                Intent qtuIntent = new Intent(v.getContext(), QuizMaster1Activity.class);
                //for test purpose
                Log.e("Quiz Count", "Quiz Cound is " + String.valueOf(db.getQuizCount()));
                QuizOptionAnswer qoaTest = db.getQuizOptionAnswer(1);
                Log.e("Question", "Question: " + qoaTest.getQuestion());
                Log.e("OptionA", "OptionA: " + qoaTest.getOptionA());
                Log.e("OptionB", "OptionB: " + qoaTest.getOptionB());
                Log.e("OptionC", "OptionC: " + qoaTest.getOptionC());
                Log.e("OptionD", "OptionD: " + qoaTest.getOptionD());
                Log.e("Answer", "Answer: " + qoaTest.getAnswer());

                startActivity(qtuIntent);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

}
