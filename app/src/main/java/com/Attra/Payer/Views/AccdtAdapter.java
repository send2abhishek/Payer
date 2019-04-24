package com.Attra.Payer.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Attra.Payer.Activities.BaseActivity;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.statementData;

import java.util.ArrayList;

public class AccdtAdapter extends RecyclerView.Adapter<TxnHistoryViewHolder> {

    private final LayoutInflater layoutInflater;
    private final BaseActivity activity;
    public final ArrayList<statementData> statementData;

    public AccdtAdapter(BaseActivity activity) {
        this.activity = activity;
        layoutInflater=activity.getLayoutInflater();
        statementData=new ArrayList<>();
    }
    public void AddDataToView(statementData data){

        statementData.add(data);
    }

    @NonNull
    @Override
    public TxnHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.txn_history_view,viewGroup,false);
        TxnHistoryViewHolder viewHolder=new TxnHistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TxnHistoryViewHolder txnHistoryViewHolder, int position) {
        statementData data=statementData.get(position);
        txnHistoryViewHolder.populate(data);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
