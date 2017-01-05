package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class Performance1Activity extends AppCompatActivity{
    TextView performanceTextView;
    Button buttonTry, buttonLogOut;
    QuizDBHelper db;
    ArrayList<Integer> record;
    HashSet<Integer> setQuestionTaken;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new QuizDBHelper(getApplicationContext());
        performanceTextView = (TextView) findViewById(R.id.performanceTextView);
        buttonTry = (Button) findViewById(R.id.buttonTry);
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);

        Intent myIntent = getIntent();
        record = myIntent.getIntegerArrayListExtra("recordQuiz");
        setQuestionTaken = (HashSet<Integer>) myIntent.getSerializableExtra("setQuestionTaken");
        userId = myIntent.getStringExtra("userId");

        float score = record.get(2) / 5f * 100;
        performanceTextView.setText(getString(R.string.finalScore,score));

        record.set(1, 0);
        record.set(2, 0);

        buttonTry.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent myIntent2 = new Intent(v.getContext(),DoQuiz1Activity.class);
                                             myIntent2.putExtra("setQuestionTaken", setQuestionTaken);
                                             myIntent2.putIntegerArrayListExtra("recordQuiz", record);
                                             myIntent2.putExtra("userId",userId);
                                             startActivity(myIntent2);
                                         }
                                     });
                buttonLogOut.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        db.updateQuizTaker(userId, setQuestionTaken);
                                                        System.exit(0);
                                                    }
                                                });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
