package com.example.eunice.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class loadingdachshund extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingdachshund);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(loadingdachshund.this,dachshund.class);
                startActivity(mainIntent);
                // 當跳到另一 Activity 時，讓 MainActivity 結束。
                // 這樣可以避免使用者按 back 後，又回到該 activity。
            }
        }, 2000);
    }
}
