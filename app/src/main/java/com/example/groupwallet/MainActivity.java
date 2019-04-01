package com.example.groupwallet;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
    private ListAdapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        listItems = new ArrayList<>();
        initData();
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

        if (listItems.size() == 0) {
            // TODO: Print no groups present
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: creating.");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        adapter = new ListAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addGroup(View view) {
        Log.d(TAG, "addGroup: Creating group.");
        Intent intent = new Intent();
        
    }
}
