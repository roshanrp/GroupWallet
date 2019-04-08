package com.example.groupwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTransaction extends AppCompatActivity {

    EditText transName;
    EditText paidBy;
    EditText transAmount;
    Button submit;
    String gId;
    String date;

    DatabaseReference databaseTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        gId = getIntent().getStringExtra("GROUP_ID");

        databaseTransaction = FirebaseDatabase.getInstance().getReference().child("transactions");
        transName = (EditText) findViewById(R.id.editTextTransName);
        paidBy = (EditText) findViewById(R.id.editTextPaidBy);
        transAmount = (EditText) findViewById(R.id.editTextTransAmount);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void submit() {
        String tName = transName.getText().toString().trim();
        String tPayer = paidBy.getText().toString().trim();
        String amount = transAmount.getText().toString().trim();
        Double tAmount = Double.parseDouble(amount);

        if (!TextUtils.isEmpty(tName) && !TextUtils.isEmpty(tPayer) && !TextUtils.isEmpty(amount)) {
            //TODO: Add to Firebase
//            String gId = databaseTransaction.push().getKey();
            String tId = databaseTransaction.push().getKey();
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            Transaction transaction = new Transaction(tId, gId, tName, tPayer, tAmount, date);
            databaseTransaction.child(tId).setValue(transaction);

            Intent intent = new Intent(this, GroupHome.class);
            intent.putExtra("GROUP_ID", gId);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show();
        }
    }
}
