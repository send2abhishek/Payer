package com.Attra.Payer.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.statementData;

public class TxnHistoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txnHeader;
    private TextView txnTime;
    private TextView txnDate;
    private TextView txnAmount;
    private CardView txnDetLayout;
    public TxnHistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txnHeader=(TextView)itemView.findViewById(R.id.txn_history_header_txt);
        txnTime=(TextView)itemView.findViewById(R.id.txn_history_time);
        txnDate=(TextView)itemView.findViewById(R.id.txn_history_date);
        txnAmount=(TextView)itemView.findViewById(R.id.txn_history_amount);
        txnDetLayout=(CardView)itemView;
    }

  public void populate(statementData data){
      itemView.setTag(data);
      txnHeader.setText(data.getMerhcnatName());
      txnTime.setText(data.getTime());
      txnDate.setText(data.getDate());
      txnAmount.setText(Double.toString(data.getAmount()));
  }
}
