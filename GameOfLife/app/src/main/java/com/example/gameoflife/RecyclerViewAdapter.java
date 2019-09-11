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
    private ArrayList<Button> bl = new ArrayList<>();

    public RecyclerViewAdapter(boolean[][] data) {
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layout = new LinearLayout(parent.getContext());
        for (int i = 0; i < data[0].length; i++) {
            Button bo = new Button(layout.getContext());
            bo.setBackground(layout.getContext().getDrawable(R.color.gray));

            bo.setLayoutParams(new LinearLayout.LayoutParams(layout.getWidth() / data[0].length, ViewGroup.LayoutParams.MATCH_PARENT));
            bo.setPadding(0, 0, 0, 0);
            bl.add(bo);


        }


        return new ViewHolda(layout, (Button[]) bl.toArray());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolda holder,final int position) {
        for (int i = 0; i < holder.linearLayout.getChildCount(); i++) {
           final Button b = (Button) holder.linearLayout.getChildAt(i);
            if (data[position][i]) {
                b.setBackground(layout.getContext().getDrawable(R.color.orange));
            } else {
                b.setBackground(layout.getContext().getDrawable(R.color.gray));


            }
            final int e=i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (b.getBackground().equals(layout.getContext().getDrawable(R.color.orange))) {

                        b.setBackground(layout.getContext().getDrawable(R.color.gray));
                        data[position][e]=false;


                    } else {

                        b.setBackground(layout.getContext().getDrawable(R.color.orange));
                        data[position][e]=true;
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

        public ViewHolda(@NonNull LinearLayout itemView, Button... b) {
            super(itemView);
            linearLayout = itemView;
            for (final Button bo : b) {
                linearLayout.addView(bo);

            }
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            itemView.setOrientation(LinearLayout.HORIZONTAL);


        }
    }

}
