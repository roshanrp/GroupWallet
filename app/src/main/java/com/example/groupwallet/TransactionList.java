package com.example.groupwallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TransactionList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        String str = getIntent().getStringExtra("GROUP_ID");
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
