package com.zopzoob.simplequizgame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private int solution;
    private int n1;
    private int n2;
    private int score;
    private int numQ;

    public void newQuestion() {
        n1 = (int)(Math.random() * 21);
        n2 = (int)(Math.random() * 21);
        solution = n1 + n2;
        int corButton = (int) (Math.random() * 4) + 1;
        TextView t1 = (TextView)findViewById(R.id.equation);
        t1.setText("" + n1 + " + " + n2 + " = ??");

        Button currB;
        for (int i = 1; i <= 4; i++) {
            if (i==1) currB = (Button) findViewById(R.id.button1);
            else if (i==2) currB = (Button) findViewById(R.id.button2);
            else if (i==3) currB = (Button) findViewById(R.id.button3);
            else currB = (Button) findViewById(R.id.button4);

            if (i==corButton) {
                currB.setText("" + solution);
            }
            else currB.setText("" + (int)(Math.random() * 40));


        }
    }

    public void solve(View view) {
        Button bb = (Button) view;
        TextView tt = (TextView) findViewById(R.id.result);

        if (Integer.parseInt(bb.getText().toString()) == solution) {
            score++;
            tt.setText("Your answer is correct!");
        }
        else {
            tt.setText("Your answer is wrong!");
        }
        numQ++;

        TextView tr = (TextView) findViewById(R.id.scoreboard);
        tr.setText("" + score + "/" + numQ);
        newQuestion();
    }

    public void gameover() {
        Button bg = (Button) findViewById(R.id.starterB);
        bg.setText("Play Again!!");
        bg.setVisibility(View.VISIBLE);
        GridLayout g1 = (GridLayout) findViewById(R.id.griddy);
        g1.getChildAt(0).setClickable(false);
        g1.getChildAt(1).setClickable(false);
        g1.getChildAt(2).setClickable(false);
        g1.getChildAt(3).setClickable(false);

        TextView end = (TextView) findViewById(R.id.result);
        end.setText("Your score: " + score + "/" + numQ);
    }

    public void begin(View view) {
        Button starter = (Button) findViewById(R.id.starterB);
        starter.setText("Play again!!");
        starter.setVisibility(View.GONE);
        score = 0;
        numQ = 0;

        TextView tt = (TextView) findViewById(R.id.result);
        tt.setVisibility(View.VISIBLE);
        tt.setText("");

        TextView tr = (TextView) findViewById(R.id.scoreboard);
        tr.setVisibility(View.VISIBLE);
        tr.setText("0/0");

        GridLayout g1 = (GridLayout) findViewById(R.id.griddy);
        g1.setVisibility(View.VISIBLE);
        g1.getChildAt(0).setClickable(true);
        g1.getChildAt(1).setClickable(true);
        g1.getChildAt(2).setClickable(true);
        g1.getChildAt(3).setClickable(true);

        TextView t1 = (TextView) findViewById(R.id.equation);
        t1.setVisibility(View.VISIBLE);

        final TextView ti = (TextView) findViewById(R.id.timetext);
        ti.setVisibility(View.VISIBLE);

        newQuestion();

        new CountDownTimer(30200, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                ti.setText("" + millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                ti.setText("0s");
                gameover();

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
