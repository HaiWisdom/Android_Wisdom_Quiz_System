package ca.uwaterloo.quiz_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class ManageQuizTaker1Activity extends AppCompatActivity {
    TextView displayTime, warning_manageTaker;
    EditText qtUserId, qtUserPassword;
    SeekBar seekBarTime;
    Button saveManageUser;
    QuizDBHelper db;
    int sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_quiz_taker1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new QuizDBHelper(getApplicationContext());
        qtUserId = (EditText) findViewById(R.id.qtUserId);
        qtUserPassword = (EditText) findViewById(R.id.qtUserPassword);
        displayTime = (TextView) findViewById(R.id.displayTime);
        warning_manageTaker = (TextView) findViewById(R.id.warning_manageTaker);
        seekBarTime = (SeekBar) findViewById(R.id.seekBarTime);

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sec = (int) (progress / 100.0 * 30.0);
                displayTime.setText(getString(R.string.setSecond,sec));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        saveManageUser = (Button) findViewById(R.id.saveManageUser);
        saveManageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = qtUserId.getText().toString();
                if (db.getUserCountForUser(userId) > 0) {
                    warning_manageTaker.setText(getString(R.string.wrongUserId));
                }
                else {
                    String userPassword = qtUserPassword.getText().toString();
                    QuizTakerUser qtu = new QuizTakerUser(userId, userPassword, sec);
                    db.createQuizTakerUser(qtu); //userId has to be unique
                    //test purpose
                    Log.d("Count of user", "Count of user" + db.getUserCount());
                    QuizTakerUser qtutest = db.getQuizTakerUser(userId);
                    Log.i("UserId", "UserId" + qtutest.getUserID());
                    Log.i("Password", "Password" + qtutest.getPassword());
                    Log.i("Time", "Time" + qtutest.getTime());
                    Intent qtuIntent = new Intent(v.getContext(), QuizMaster1Activity.class);
                    startActivity(qtuIntent);
                }
            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
