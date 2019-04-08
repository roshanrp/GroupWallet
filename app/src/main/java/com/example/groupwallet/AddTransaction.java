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

public class AddTransaction extends AppCompatActivity {

    EditText transName;
    EditText paidBy;
    EditText transAmount;
    Button submit;

    DatabaseReference databaseTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        databaseTransaction = FirebaseDatabase.getInstance().getReference("transactions");
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
        Toast.makeText(this, "Submit Clicked", Toast.LENGTH_LONG).show();
        String tName = transName.getText().toString().trim();
        String tPayer = paidBy.getText().toString().trim();
        String amount = transAmount.getText().toString().trim();
        Double tAmount = Double.parseDouble(amount);

        if (!TextUtils.isEmpty(tName) && !TextUtils.isEmpty(tPayer) && !TextUtils.isEmpty(amount)) {
            //TODO: Add to Firebase
            String gId = databaseTransaction.push().getKey();
            String tId = databaseTransaction.child(gId).push().getKey();
            Transaction transaction = new Transaction(gId, tId, tName, tPayer, tAmount);
            databaseTransaction.child(gId).setValue(transaction);

        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, GroupHome.class);
        startActivity(intent);

    }
}
