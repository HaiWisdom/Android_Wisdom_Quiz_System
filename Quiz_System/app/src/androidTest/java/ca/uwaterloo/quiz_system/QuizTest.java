package ca.uwaterloo.quiz_system;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Wisdom H Jiang on 2016-10-27.
 */

@RunWith(AndroidJUnit4.class)
public class QuizTest {
    @Rule
    public ActivityTestRule<Welcome1Activity> welcome1ActivityActivityTestRule =
            new ActivityTestRule<>(Welcome1Activity.class);
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), welcome1ActivityActivityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @Test
    public void testQMManageQT() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        solo.clickOnView(solo.getView(R.id.buttonQmLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQT));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected ManageQuizTaker", ManageQuizTaker1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.qtUserId), "h57jiang1");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtUserPassword), "12345");
        solo.sleep(1000);
        solo.setProgressBar((ProgressBar) solo.getView(R.id.seekBarTime), 50);
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.saveManageUser));
        solo.sleep(1000);
    }

    @Test
    public void testQMManageQuiz() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        solo.clickOnView(solo.getView(R.id.buttonQmLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is Toronto");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "A");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(10000);
    }


    @Test
    public void testQT() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        solo.clickOnView(solo.getView(R.id.buttonQTLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizTaker1Activity", QuizTaker1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.qtLoginUserId), "h");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginPassword), "12345");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonUserLogin));
        solo.sleep(1000);
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginUserId));
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginPassword));
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginUserId), "h57jiang1");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginPassword), "123456");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonUserLogin));
        solo.sleep(1000);
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginUserId));
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginPassword));
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginUserId), "h57jiang1");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginPassword), "12345");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonUserLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected Welcome1Activity", DoQuiz1Activity.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.radio_b));
        solo.sleep(10000);
    }

    @Test
    public void testQMManageQuizFiveQuiz() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        solo.clickOnView(solo.getView(R.id.buttonQmLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is Beijing new");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "D");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is WashingtonDC new");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "B");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is Sydney new");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "C");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is Chengdu new");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "D");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizMaster", QuizMaster1Activity.class);
        solo.clickOnView(solo.getView(R.id.buttonManageQuiz));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected AddQuiz1Activity", AddQuiz1Activity.class);
        solo.enterText((EditText) solo.getView(R.id.editQuestion), "Where is Chengdu new2");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionA), "In Canada");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionB), "In USA");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionC), "In Australia");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editOptionD), "In China");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.editRightAnswer), "D");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonSubmitNewQuiz));
        solo.sleep(1000);
    }


    @Test
    public void testQT1() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        solo.clickOnView(solo.getView(R.id.buttonQTLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizTaker1Activity", QuizTaker1Activity.class);
        solo.sleep(1000);
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginUserId));
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginPassword));
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginUserId), "h57jiang1");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginPassword), "12345");
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonUserLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected DoQuiz1Activity", DoQuiz1Activity.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.radio_b));
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonNext));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected DoQuiz1Activity", DoQuiz1Activity.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.radio_d));
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonNext));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected DoQuiz1Activity", DoQuiz1Activity.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.radio_c));
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonNext));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected DoQuiz1Activity", DoQuiz1Activity.class);
        solo.sleep(3000);
        solo.clickOnView(solo.getView(R.id.radio_d));
        solo.sleep(1000);
        solo.clickOnView(solo.getView(R.id.buttonNext));
        solo.sleep(1000);
        solo.sleep(1000000);
    }

    @Test
    public void testQT2() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected Welcome1Activity", Welcome1Activity.class);
        //test input with single digit
        /*solo.clickOnView(solo.getView(R.id.buttonQTLogin));
        solo.sleep(1000);
        solo.assertCurrentActivity("Expected QuizTaker1Activity", QuizTaker1Activity.class);
        solo.sleep(1000);
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginUserId));
        solo.clearEditText((EditText) solo.getView(R.id.qtLoginPassword));
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginUserId), "h57jiang1");
        solo.sleep(1000);
        solo.enterText((EditText) solo.getView(R.id.qtLoginPassword), "12345");
        solo.sleep(1000);*/
        //solo.clickOnView(solo.getView(R.id.buttonUserLogin));
        //solo.sleep(1000);
        //solo.assertCurrentActivity("Expected DoQuiz1Activity", DoQuiz1Activity.class);
        solo.sleep(20000000);
    }

}
