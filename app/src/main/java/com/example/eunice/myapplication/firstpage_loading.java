package com.example.eunice.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class firstpage_loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage_loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(firstpage_loading.this,firstpage_loading1.class);
                startActivity(mainIntent);
                    // 當跳到另一 Activity 時，讓 MainActivity 結束。
                // 這樣可以避免使用者按 back 後，又回到該 activity。
            }
        }, 3000);



        GifImageView gifImageView1 = (GifImageView) findViewById(R.id.gif3);
       GifImageView gifImageView2 = (GifImageView) findViewById(R.id.gifImageView3);


        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.logo);
            gifImageView1.setImageDrawable(gifDrawable);
           GifDrawable gifDrawables = new GifDrawable(getResources(), R.drawable.loading);
            gifImageView2.setImageDrawable(gifDrawables);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
