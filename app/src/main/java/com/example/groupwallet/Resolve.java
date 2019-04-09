package com.example.groupwallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Resolve extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve);

        String text = getIntent().getStringExtra("Resolve");
        textView = (TextView) findViewById(R.id.resolve);
        textView.setText(text);
    }
}
