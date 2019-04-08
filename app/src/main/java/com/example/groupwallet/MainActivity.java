package com.example.groupwallet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private ListAdapter adapter;
//    public List<ListItem> listItems;
    List<Group> groupList;
    GroupList adapter;
    DatabaseReference databaseGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        //TODO: SetPersistentEnabled.
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        groupList = new ArrayList<>();

        initRecyclerView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseGroup = FirebaseDatabase.getInstance().getReference().child("groups");

        databaseGroup.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                groupList.clear();
                for(DataSnapshot groupSnapshot : dataSnapshot.getChildren()) {
                    Group group = groupSnapshot.getValue(Group.class);
                    groupList.add(group);
                }

                if (groupList.size() == 0) {
                    TextView noGroup = MainActivity.this.findViewById(R.id.noGroups);
                    noGroup.setVisibility(View.VISIBLE);
                }

                GroupList adapter = new GroupList(MainActivity.this, groupList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initData() {
        Log.d(TAG, "initData: Preparing Groups");
//        for (int i = 0; i <= 10; i++) {
//            ListItem listItem = new ListItem(
//                    "GroupName" + i,
//                    "GroupDescription"
//            );
//
//            listItems.add(listItem);
//        }

//        for (int i = 0; i <= 10; i++) {
//            Group group = new Group(
//                    "" + i,
//                    "GroupName" + i,
//                    "GroupDescription"
//            );
//
//            groupList.add(group);
//        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ListItem listItem = new ListItem(
//                    "GroupName",
//                    "GroupDescription");
//                listItems.add(listItem);
//            }
//        });


    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: creating.");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        adapter = new ListAdapter(listItems, this);
        adapter = new GroupList(this, groupList);
        recyclerView.setAdapter(adapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addGroup(View view) {
        Log.d(TAG, "addGroup: Creating group.");
        Intent intent = new Intent(this, AddGroup.class);
        startActivity(intent);
    }
}
