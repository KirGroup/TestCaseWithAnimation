package com.example.testcasewithanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anim animForText = new Anim(this);

        animForText.initHelloTextView(findViewById(R.id.main_text));
        animForText.initMainBox(findViewById(R.id.main_box));
    }
}