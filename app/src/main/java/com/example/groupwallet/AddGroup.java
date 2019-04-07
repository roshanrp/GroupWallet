package com.example.groupwallet;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Collection;

public class AddGroup extends AppCompatActivity {

    String name;
    String desc;
    //String[] members;
    Collection<Participant> members;

    EditText groupName;
    EditText groupDesc;
    EditText memberList;
    Button submit;

    DatabaseReference databaseGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        databaseGroup = FirebaseDatabase.getInstance().getReference("groups");
        groupName = (EditText) findViewById(R.id.editTextGroupName);
        groupDesc = (EditText) findViewById(R.id.editTextGroupDescription);
        memberList = (EditText) findViewById(R.id.editTextMembers);
        submit = (Button) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private String[] getMembers() {
        String[] members;
        members = memberList.getText().toString().split(",");
        for (String member : members) {
            member.trim();
        }

        return members;
    }

    public void submit() {

        Toast.makeText(this, "SubmitClicked", Toast.LENGTH_LONG).show();
        String gName = groupName.getText().toString().trim();
        String gDesc = groupDesc.getText().toString().trim();
        String[] membersArray = getMembers();
//        Collection<Participant> = new Collection<Participant>(Arrays.asList(membersArray));

//        if (!TextUtils.isEmpty(gName) && !TextUtils.isEmpty(gDesc) && members.length != 0) {
        if (!TextUtils.isEmpty(gName) && !TextUtils.isEmpty(gDesc)) {
            //TODO: Add to firebase

            String id = databaseGroup.push().getKey();

//            Group group = new Group(id, gName, gDesc, membersArray[0]);
            Group group = new Group(id, gName, gDesc);
            databaseGroup.child(id).setValue(group);

            Toast.makeText(this, "Group created", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
