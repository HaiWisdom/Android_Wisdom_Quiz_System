package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizTaker1Activity extends AppCompatActivity {
    TextView warning;
    EditText qtLoginUserId, qtLoginPassword;
    Button buttonUserLogin;
    QuizDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taker1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new QuizDBHelper(getApplicationContext());
        qtLoginUserId = (EditText) findViewById(R.id.qtLoginUserId);
        qtLoginPassword = (EditText) findViewById(R.id.qtLoginPassword);
        warning = (TextView) findViewById(R.id.warning);

        buttonUserLogin = (Button) findViewById(R.id.buttonUserLogin);
        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = qtLoginUserId.getText().toString();
                QuizTakerUser qtu = db.getQuizTakerUser(userId);
                if (qtu == null || !qtu.getPassword().equals(qtLoginPassword.getText().toString())) {
                    warning.setText(getString(R.string.incorrectUserID));
                }
                else {
                    String strQuestionTaken = qtu.getQuizTaken();
                    String[] arrayQuestionTaken = strQuestionTaken.split(",");
                    HashSet<Integer> setQuestionTaken = new HashSet<>();
                    for (String s : arrayQuestionTaken) {
                        setQuestionTaken.add(Integer.parseInt(s));
                    }
                    ArrayList<Integer> recordQuiz = new ArrayList<Integer>();
                    recordQuiz.add(qtu.getTime());
                    recordQuiz.add(0);
                    recordQuiz.add(0);
                    qtu.setQuizTaken("");
                    Intent myIntent = new Intent(v.getContext(),DoQuiz1Activity.class);
                    myIntent.putExtra("setQuestionTaken", setQuestionTaken);
                    myIntent.putIntegerArrayListExtra("recordQuiz", recordQuiz);
                    myIntent.putExtra("userId",userId);
                    startActivity(myIntent);
                }
            }
        });


/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
