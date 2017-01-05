package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QuizMaster1Activity extends AppCompatActivity {
    Button buttonManageQT, buttonManageQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_master1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonManageQT = (Button) findViewById(R.id.buttonManageQT);
        buttonManageQT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mqtIntent = new Intent(v.getContext(), ManageQuizTaker1Activity.class);
                startActivity(mqtIntent);
            }
        });

        buttonManageQuiz = (Button) findViewById(R.id.buttonManageQuiz);
        buttonManageQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mqIntent = new Intent(v.getContext(), AddQuiz1Activity.class);
                startActivity(mqIntent);
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
