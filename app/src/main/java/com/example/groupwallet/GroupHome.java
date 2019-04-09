package com.example.groupwallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GroupHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    TransactionList adapter;
    List<Transaction> transactionList;
    DatabaseReference databaseTransaction;
    List<Participant> membersList;
    Button resolve;
    String gId;
    private static final String TAG = "GroupHome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);

        gId = getIntent().getStringExtra("GROUP_ID");

        transactionList = new ArrayList<>();
        initRecyclerView();

//        resolve = (Button) findViewById(R.id.split);
//        resolve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(GroupHome.this, "Resolve", Toast.LENGTH_LONG).show();
//                List<Participant> groupMembers = getMembers();
//                Log.d(TAG, "resolve: members fetched");
//            }
//        });

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
                    if (transaction.getGroupId().equals(gId)) {
                        transactionList.add(transaction);
                    }
                }

                if (transactionList.size() == 0) {
                    TextView noTrans = GroupHome.this.findViewById(R.id.noTrans);
                    noTrans.setVisibility(View.VISIBLE);
                } else {
                    TextView noTrans = GroupHome.this.findViewById(R.id.noTrans);
                    noTrans.setVisibility(View.GONE);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void addTransaction(View view) {
        Intent intent = new Intent(this, AddTransaction.class);
        intent.putExtra("GROUP_ID", gId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    public void resolve(View view) {

        List<Participant> groupMembers = getMembers();
//        Log.d(TAG, "resolve: size  " + groupMembers.size());
//        double payment;
//        String resolveText = new String("Hello ");
//        for (Participant p : groupMembers) {
//            Log.d(TAG, "resolve: inLoop");
//            payment = p.getAmountPaid() - p.getShare();
//            //resolveText = resolveText + p.getMemberName() + " owes " + Double.toString(payment) + " ";
//            String Name = p.getMemberName();
//            resolveText = resolveText + Name;
//        }
//        Log.d(TAG, "resolve: Resolve Text" + resolveText);

    }

    public List<Participant> getMembers() {
         membersList = new ArrayList<>();
        databaseTransaction = FirebaseDatabase.getInstance().getReference().child("groups");
        DatabaseReference memberRef = databaseTransaction.child(gId).child("members");
        Log.d(TAG, "onDataChange: listgg " + membersList.size());


        memberRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot memberSnapshot : dataSnapshot.getChildren()) {
                    Participant member = memberSnapshot.getValue(Participant.class);
                    Log.d(TAG, "onDataChange: Name" + member.getMemberName());
                    membersList.add(member);
                    Log.d(TAG, "onDataChange: list " + membersList.size());
                }

                DecimalFormat precision = new DecimalFormat("0.00");
                double payment;
                String resolveText = "";
                for (Participant p : membersList) {
                    payment = p.getAmountPaid() - p.getShare();
                    if (payment < 0) {
                        payment = Math.abs(payment);
                        resolveText = resolveText + p.getMemberName() + " has to pay " + precision.format(payment) + "\n";
                    } else {
                        resolveText = resolveText + p.getMemberName() + " should get " + precision.format(payment) + "\n";
                    }
                }

                Intent intent = new Intent(GroupHome.this, Resolve.class);
                intent.putExtra("Resolve", resolveText);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return membersList;
    }
}
