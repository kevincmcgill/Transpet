package com.example.eunice.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class loginin extends AppCompatActivity {

    private ImageButton mButton;
    private EditText mPasswordView;

    private TextView mLoginStatusMessageView;

    private Intent intent;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin);
/*
        mButton = findViewById(R.id.imageButton2);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(loginin.this,homepoodle.class);
                startActivity(intent);
                loginin.this.finish();
            }
        });
        mButton = findViewById(R.id.imageButton);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(loginin.this,homeshibainu.class);
                startActivity(intent);
                loginin.this.finish();
            }
        });
        mButton = findViewById(R.id.imageButton3);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(loginin.this,homebeagle.class);
                startActivity(intent);
                loginin.this.finish();
            }
        });
        mButton = findViewById(R.id.imageButton4);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(loginin.this,homecorgi.class);
                startActivity(intent);
                loginin.this.finish();
            }
        });
        mButton = findViewById(R.id.imageButton5);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(loginin.this,dachshund.class);
                startActivity(intent);
                loginin.this.finish();
            }
        });

*/




        mLoginStatusMessageView = (TextView)findViewById(R.id.sign_in_button);

        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(mPasswordView.getText().toString().equals("1")){

                    intent = new Intent(loginin.this,homebeagle.class);

                    startActivity(intent);

                }
            }

            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override


            public void afterTextChanged(Editable s) {


            }


        });

        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(mPasswordView.getText().toString().equals("2")){

                    intent = new Intent(loginin.this,homecorgi.class);

                    startActivity(intent);

                }
            }

            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override


            public void afterTextChanged(Editable s) {


            }


        });

        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(mPasswordView.getText().toString().equals("3")){

                    intent = new Intent(loginin.this,homepoodle.class);

                    startActivity(intent);

                }
            }

            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override


            public void afterTextChanged(Editable s) {


            }


        });

        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(mPasswordView.getText().toString().equals("4")){

                    intent = new Intent(loginin.this,homeshibainu.class);

                    startActivity(intent);

                }
            }

            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override


            public void afterTextChanged(Editable s) {


            }


        });

        mPasswordView.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(mPasswordView.getText().toString().equals("5")){

                    intent = new Intent(loginin.this,dachshund.class);

                    startActivity(intent);

                }
            }

            @Override


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override


            public void afterTextChanged(Editable s) {


            }


        });


    }


    @Override


    protected void onResume() {


// 由於我知道你會點擊返回鍵反復試驗，所以加了這句話


        mPasswordView.setText("");


        super.onResume();


    }
    }
