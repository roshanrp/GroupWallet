package com.example.groupwallet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GroupList extends RecyclerView.Adapter<GroupList.GroupViewHolder> {

    private Context context;
    private List<Group> groupList;
    private static final String TAG = "GroupList";

    public GroupList(Context context, List<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new GroupViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        final Group group = groupList.get(i);
        groupViewHolder.groupName.setText(group.getGroupName());
        groupViewHolder.groupDesc.setText(group.getGroupDesc());

        groupViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + group);
                Intent intent = new Intent(context, GroupHome.class);
                intent.putExtra("GROUP_ID", group.getGroupId());
                intent.putExtra("GROUP_NAME", group.getGroupName());
                intent.putExtra("GROUP_DESC", group.getGroupDesc());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    //RecyclerView.ViewHolder
    class GroupViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        TextView groupName;
        TextView groupDesc;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
            groupName = (TextView) itemView.findViewById(R.id.groupName);
            groupDesc = (TextView) itemView.findViewById(R.id.groupDescription);
        }
    }
}
