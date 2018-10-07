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


public class listcorgi extends AppCompatActivity {
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
    private String[] feedback1 = {"I am really scared! Don't hurt me ( ́;ω;`)", "So terrible (((ﾟДﾟ;)))"};
    private String[] feedback2 = {"Enough!!! (๑•ૅω• ́๑)", "Have a fight(#`Д ́)ノ"," Get out of my way (๑•ૅω• ́๑)"};


    private Button mButton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcorgi);



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

                if(radioButton.isChecked()){
                    answer = "I don't like it, I want to run away (`Д ́)ノ";
                }
                else if(radioButton2.isChecked()){
                    answer = "I am the boss , get out of my wayヽ(#`Д´)ﾉ";
                }
                else if(radioButton3.isChecked()){
                    answer = "Go away! Don't bother me ( ́;ω;`)";
                }
                else if(radioButton4.isChecked()){
                    answer = "Don't hurt me ( ́;ω;`)";
                }
                else if(radioButton5.isChecked()){
                    answer = "I am so hungry! I have to eat (#`Д ́)ノ";
                }
                else if(radioButton7.isChecked()){
                    answer = "Let us decide who is the boss here ( ́•д• ̀ლ";
                }
                else if (radioButton8.isChecked()) {
                    for (int i = 1; i <= 1; i++) {
                        int a = random.nextInt(feedback1.length);
                        answer = feedback1[a];
                    }
                }
                else if(radioButton9.isChecked()){
                    for (int i = 1; i <= 1; i++) {
                        int a = random.nextInt(feedback2.length);
                        answer = feedback2[a];
                    }

                }




                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("Answer", answer.toString());
                intent.putExtras(bundle);
                intent.setClass(listcorgi.this, angrycorgi.class);

                startActivity(intent);
            }
        });
             /*   mButton = findViewById(R.id.imageButton6);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                 intent.setClass(listcorgi.this,loadingcorgi.class);
                startActivity(intent);
                listcorgi.this.finish();
            }
        });

        */


    }
}
