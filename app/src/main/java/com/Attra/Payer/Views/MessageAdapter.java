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


public class MessageAdapter extends RecyclerView.Adapter<TxnHistoryViewHolder> implements View.OnClickListener {

    private final LayoutInflater layoutInflater;
    private final BaseActivity activity;
    private final onMessageClickListener listener;
    public final ArrayList<statementData> statementData;

    public MessageAdapter(BaseActivity activity, onMessageClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        layoutInflater=activity.getLayoutInflater();
        statementData=new ArrayList<>();

    }

    public ArrayList<statementData> getstatementData() {
        return statementData;
    }

    public void AddDataToView(statementData data){

        statementData.add(data);
    }

    @NonNull
    @Override
    public TxnHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=layoutInflater.inflate(R.layout.txn_history_view,viewGroup,false);
        view.setOnClickListener(this);
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
        return statementData.size();
    }

    @Override
    public void onClick(View view) {

        if(view.getTag() instanceof statementData){

            statementData data=(statementData)view.getTag();
            listener.OnmessageClicked(data);
        }

    }

    public interface onMessageClickListener{

        void OnmessageClicked(statementData statementData);
    }
}
