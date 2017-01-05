package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Welcome1Activity extends AppCompatActivity {
    Button buttonQmLogin, buttonQTLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonQmLogin = (Button) findViewById(R.id.buttonQmLogin);
        buttonQmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qmIntent = new Intent(v.getContext(), QuizMaster1Activity.class);
                startActivity(qmIntent);
            }
        });
        buttonQTLogin = (Button) findViewById(R.id.buttonQTLogin);
        buttonQTLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qtIntent = new Intent(v.getContext(), QuizTaker1Activity.class);
                startActivity(qtIntent);
            }
        });



/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



}}
