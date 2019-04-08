package com.example.groupwallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionList extends RecyclerView.Adapter<TransactionList.TransactionViewHolder> {

    private Context context;
    private List<Transaction> transactionList;
    private static final String TAG = "TransactionList";

    public TransactionList(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transaction_listitem, viewGroup, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int i) {

        Transaction transaction = transactionList.get(i);
        transactionViewHolder.transName.setText(transaction.getTransName());
        transactionViewHolder.paidBy.setText("Paid By: " + transaction.getPaidBy());
        transactionViewHolder.amount.setText(transaction.getAmount().toString());
        transactionViewHolder.date.setText(transaction.getDate());
        //TODO: Add on CLick (Secondary).


    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView transName;
        TextView paidBy;
        TextView amount;
        TextView date;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
            transName = (TextView) itemView.findViewById(R.id.transName);
            paidBy = (TextView) itemView.findViewById(R.id.paidBy);
            amount = (TextView) itemView.findViewById(R.id.transAmount);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
