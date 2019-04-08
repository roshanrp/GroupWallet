package com.example.groupwallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    TransactionList adapter;
    List<Transaction> transactionList;
    DatabaseReference databaseTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);

        String str = getIntent().getStringExtra("GROUP_ID");
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

        transactionList = new ArrayList<>();
        initRecyclerView();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTransaction = FirebaseDatabase.getInstance().getReference().child("transactions");
        databaseTransaction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transactionList.clear();
                for(DataSnapshot transactionSnapshot : dataSnapshot.getChildren()) {
                    Transaction transaction = transactionSnapshot.getValue(Transaction.class);
                    transactionList.add(transaction);
                }

                if (transactionList.size() == 0) {
                    TextView noTrans = GroupHome.this.findViewById(R.id.noTrans);
                    noTrans.setVisibility(View.VISIBLE);
                }

                TransactionList adapter = new TransactionList(GroupHome.this, transactionList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionList(this, transactionList);
        recyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void addTransaction(View view) {
        Intent intent = new Intent(this, AddTransaction.class);
        startActivity(intent);
    }
}
