package ca.uwaterloo.quiz_system;

/**
 * Created by Wisdom H Jiang on 2016-10-21.
 */

public class QuizTakerUser {
    private String userID;
    private String password;
    private int time;
    private String quizTaken;

    public QuizTakerUser(){
    }

    public QuizTakerUser(String userID, String password, int time) {
        this.userID = userID;
        this.password = password;
        this.time = time;
        quizTaken = "";
        //theNth = 0;
        //correctAnswer = 0;
    }

    public void setQuizTaken(String quizTaken){
        this.quizTaken = quizTaken;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    /*public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }

    /*public void setTheNth(int theNth) {
        this.theNth = theNth;
    }*/

    public void setTime(int time) {
        this.time = time;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getPassword(){
        return this.password;
    }

    public String getQuizTaken(){
        return this.quizTaken;
    }

    public int getTime(){
        return this.time;
    }

    /*public int getCorrectAnswer() {
        return correctAnswer;
    }*/

    /*public int getTheNth() {
        return theNth;
    }*/
}
