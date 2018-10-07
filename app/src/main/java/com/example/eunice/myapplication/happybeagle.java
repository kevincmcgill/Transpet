package com.example.eunice.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class happybeagle extends AppCompatActivity {
    Bundle bundle;
    Intent intent;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happybeagle);

        textView = findViewById(R.id.textView);

        intent = this.getIntent();
        bundle = intent.getExtras();
        String advice = bundle.getString("Answer");
        textView.setText(advice);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(happybeagle.this, loadingbeagle.class);
                startActivity(mainIntent);
                // 當跳到另一 Activity 時，讓 MainActivity 結束。
                // 這樣可以避免使用者按 back 後，又回到該 activity。
            }
        }, 6000);
    }
}
