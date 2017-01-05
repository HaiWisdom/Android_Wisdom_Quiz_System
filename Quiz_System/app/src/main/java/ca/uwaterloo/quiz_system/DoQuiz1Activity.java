package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class DoQuiz1Activity extends AppCompatActivity {
    TextView questionNumber, remainingTime, questionOption, answerTextView;
    Button buttonNext;
    RadioGroup radioGroup;
    RadioButton optionA, optionB, optionC, optionD;
    QuizDBHelper db;
    ArrayList<Integer> record;
    HashSet<Integer> setQuestionTaken;
    QuizOptionAnswer qoa;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_quiz1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new QuizDBHelper(getApplicationContext());
        questionNumber = (TextView) findViewById(R.id.questionNumber);
        remainingTime = (TextView) findViewById(R.id.remainingTime);
        questionOption = (TextView) findViewById(R.id.questionOption);
        answerTextView = (TextView) findViewById(R.id.answerTextView);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        optionA = (RadioButton) findViewById(R.id.radio_a);
        optionB = (RadioButton) findViewById(R.id.radio_b);
        optionC = (RadioButton) findViewById(R.id.radio_c);
        optionD = (RadioButton) findViewById(R.id.radio_d);

        Intent myIntent = getIntent();
        record = myIntent.getIntegerArrayListExtra("recordQuiz");
        setQuestionTaken = (HashSet<Integer>) myIntent.getSerializableExtra("setQuestionTaken");
        userId = myIntent.getStringExtra("userId");

        displayQuestion();

        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                Intent myIntent2;
                if (record.get(1) == 5) {
                    myIntent2 = new Intent(v.getContext(),Performance1Activity.class);
                }
                else {
                    myIntent2 = new Intent(v.getContext(),DoQuiz1Activity.class);
                }
                myIntent2.putExtra("setQuestionTaken", setQuestionTaken);
                myIntent2.putIntegerArrayListExtra("recordQuiz", record);
                myIntent2.putExtra("userId",userId);
                sleepForTwoSecond();
                startActivity(myIntent2);
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

    private void sleepForTwoSecond(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             }
        }).start();
    }

    private void displayQuestion(){
        int maxIndex = db.getQuizCount();
        int quizIndex = -1;
        while (quizIndex == -1 || setQuestionTaken.contains(quizIndex)){
            quizIndex = ThreadLocalRandom.current().nextInt(1, maxIndex + 1);
        }
        setQuestionTaken.add(quizIndex);
        qoa = db.getQuizOptionAnswer(quizIndex);
        int theNth = record.get(1);
        questionNumber.setText(getString(R.string.question1,++theNth));
        questionOption.setText(getString(R.string.questionFormat,quizIndex, qoa.getQuestion()
                ,qoa.getOptionA(),qoa.getOptionB(),qoa.getOptionC(),qoa.getOptionD()));
        record.set(1, theNth);

        new CountDownTimer(record.get(0) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                remainingTime.setText(getString(R.string.remainingTime1,millisUntilFinished / 1000));
            }

            public void onFinish() {
                remainingTime.setText(getString(R.string.timeEnd));
                checkAnswer();
                Intent myIntent2;
                if (record.get(1) == 5) {
                    myIntent2 = new Intent(getApplicationContext(),Performance1Activity.class);
                }
                else {
                    myIntent2 = new Intent(getApplicationContext(),DoQuiz1Activity.class);
                }
                myIntent2.putExtra("setQuestionTaken", setQuestionTaken);
                myIntent2.putIntegerArrayListExtra("recordQuiz", record);
                myIntent2.putExtra("userId",userId);
                sleepForTwoSecond();
                startActivity(myIntent2);
            }
        }.start();
    }

    private void checkAnswer(){
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        String currentAnswer;
        if (rb == optionA) {
            currentAnswer = "A";
        }
        else if (rb == optionB) {
            currentAnswer = "B";
        }
        else if (rb == optionC) {
            currentAnswer = "C";
        }
        else if (rb == optionD) {
            currentAnswer = "D";
        }
        else {
            currentAnswer = "";
        }
        if (qoa.getAnswer().equals(currentAnswer)){
            answerTextView.setText(R.string.correctAns);
            answerTextView.setTextColor(getResources().getColor(R.color.correct_answer,getTheme()));
            record.set(2, record.get(2) + 1);
        }
        else {
            answerTextView.setText(R.string.incorrectAns);
            answerTextView.setTextColor(getResources().getColor(R.color.incorrect_answer,getTheme()));
        }
    }

}
