package com.example.gameoflife;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolda> {

    private boolean[][] data;
    private LinearLayout layout;


    public RecyclerViewAdapter(boolean[][] data) {
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layout = new LinearLayout(parent.getContext());


        return new ViewHolda(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolda holder, final int position) {
        for (int i = 0; i < holder.linearLayout.getChildCount(); i++) {
            final Button b = (Button) holder.linearLayout.getChildAt(i);
            if (data[position][i]) {
                b.setBackground(layout.getContext().getDrawable(R.color.orange));
            } else {
                b.setBackground(layout.getContext().getDrawable(R.color.gray));


            }
            final int e = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (b.getBackground().equals(layout.getContext().getDrawable(R.color.orange))) {

                        b.setBackground(layout.getContext().getDrawable(R.color.gray));
                        data[position][e] = false;
                        RecyclerViewAdapter.this.notifyItemChanged(position);

                    } else {

                        b.setBackground(layout.getContext().getDrawable(R.color.orange));

                        data[position][e] = true;
                        RecyclerViewAdapter.this.notifyItemChanged(position);
                    }
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolda extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;

        public ViewHolda(@NonNull LinearLayout itemView) {
            super(itemView);
            linearLayout = itemView;
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setPadding(0, 0, 0, 0);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < data[0].length; i++) {
                Button bo = new Button(layout.getContext());

                linearLayout.addView(bo);
                bo.setBackground(layout.getContext().getDrawable(R.color.gray));
                int distance =layout.getWidth() / data[0].length;
                LinearLayout.LayoutParams lp =  new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(40, 40));
                lp.setMargins(0,0,0,0);
                bo.setLayoutParams(lp);
                bo.setPadding(0, 0, 0, 0);

            }



        }
    }

}
