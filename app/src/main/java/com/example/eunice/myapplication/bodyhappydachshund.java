package com.example.eunice.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import java.util.Random;


public class bodyhappydachshund extends AppCompatActivity {
    Random random = new Random();

    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private RadioButton radioButton7;
    private RadioButton radioButton8;
    private RadioButton radioButton9;
    private int mood;
    private String[] feedback = {"I know you like me right (❛◡❛✿) ","Man, what are you thinking? I am here!"};
    private String[] feedback1 = {"I like you ✧*.٩(ˊᗜˋ*)و✧", "Let's be friends ✧*.٩(ˊᗜˋ*)و✧*"};


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyhappydachshund);


    radioButton = findViewById(R.id.radioButton);
    radioButton2 = findViewById(R.id.radioButton2);
    radioButton3 = findViewById(R.id.radioButton3);
    radioButton4 = findViewById(R.id.radioButton4);
    radioButton5 = findViewById(R.id.radioButton5);
    radioButton7 = findViewById(R.id.radioButton7);
    radioButton8 = findViewById(R.id.radioButton8);
        radioButton9 = findViewById(R.id.radioButton9);


    ImageButton imageButton6 = findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {

            String answer = "";
            if (radioButton.isChecked()) {
                answer = "Is there something new ? (◕∀◕.)";
            } else if (radioButton2.isChecked()) {
                answer = "You are my favorite ヽ(✿ﾟ▽ﾟ)ノ";
            } else if (radioButton3.isChecked()) {
                answer = "Hi! We can play together (* ́∀`)~♥";
            } else if (radioButton4.isChecked()) {
                answer = "I am not really threatening you (つд⊂)";
            } else if (radioButton5.isChecked()) {
                answer = "I am relaxed and happyヽ(✿ﾟ▽ﾟ)ノ";

            } else if (radioButton9.isChecked()) {
                answer = "I am excited ♥♥♥♥♥";

            }else if (radioButton7.isChecked()) {
                for (int i = 1; i <= 1; i++) {
                    int a = random.nextInt(feedback.length);
                    answer = feedback[a];
                }
            } else if (radioButton8.isChecked()) {
                for (int i = 1; i <= 1; i++) {
                    int b = random.nextInt(feedback1.length);
                    answer = feedback1[b];
                }
            }
/*
            if (radioButton.isChecked()) {
                answer = "我不喜歡這樣，我要逃跑ヽ(`Д´)ノ";
            } else if (radioButton2.isChecked()) {
                answer = "我才是這裡的老大，你給我滾出去ヽ(#`Д´)ﾉ";
            } else if (radioButton3.isChecked()) {
                answer = "走開！別煩我(´;ω;`)";
            } else if (radioButton4.isChecked()) {
                answer = "你別傷害我(´;ω;`)";
            } else if (radioButton5.isChecked()) {
                answer = "我好餓喔！我要吃飯了(#`Д´)ﾉ";
            } else if (radioButton7.isChecked()) {
                answer = "讓我們決定一下誰才是這裡的老大ლ(´•д• ̀ლ";
            } else if (radioButton8.isChecked()) {
                answer = "好可怕哦(((ﾟДﾟ;)))";
            } else if (radioButton.isChecked()) {
                answer = "我好害怕！別傷害我(´;ω;`)";
            } else if (radioButton.isChecked()) {
                answer = "我要向你挑戰(╯‵□′)╯︵┴─┴";
            } else if (radioButton.isChecked()) {
                answer = "緊張指數100% 登登登இдஇ";
            } else if (radioButton.isChecked()) {
                answer = "我受夠你了(๑•ૅω•´๑)\n+出招吧！看你是要立刻放棄、開戰、或滾蛋(๑•ૅω•´๑)";
            }
*/

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("Answer", answer.toString());
            intent.putExtras(bundle);
            intent.setClass(bodyhappydachshund.this, happydachshund.class);

            startActivity(intent);
        }
    });

        /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyhappydachshund);

        mButton = findViewById(R.id.imageButton6);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(bodyhappydachshund.this,loadingdachshund.class);
                startActivity(intent);
                //loginin.this.finish();
            }
        });
        */
    }
}
