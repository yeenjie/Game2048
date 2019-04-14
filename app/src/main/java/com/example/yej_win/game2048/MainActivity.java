package com.example.yej_win.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public MainActivity(){
        mainActivity = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = (TextView) findViewById(R.id.tvScore);
        tvScore.setTextSize(20);
    }

    public void clearScore(){
        this.score = 0;
//        showScore();
    }

    public void showScore(){
        tvScore.setText(score+"");
    }

    public void addScore(int addScore){
        this.score += addScore;
        showScore();
    }
    private int score;
    private TextView tvScore;
    private static MainActivity mainActivity =null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
