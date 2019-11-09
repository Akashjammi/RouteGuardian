package com.jammi.akash.schoolbustracker.Adapter;

import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jammi.akash.schoolbustracker.CustomClass.CalenderAdapter;
import com.jammi.akash.schoolbustracker.R;

import java.util.List;

public class cal_rv_adapter extends RecyclerView.Adapter<cal_rv_adapter.MyViewHolder> {

    private List<CalenderAdapter> DateList;
    CalenderAdapter cA;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        CardView mCardView;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            mCardView=(CardView) view.findViewById(R.id.date_cv);

        }
    }


    public cal_rv_adapter(List<CalenderAdapter> DateList) {
        this.DateList = DateList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_rv, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CalenderAdapter movie = DateList.get(position);
        holder.date.setText(movie.getTitle());
        int colorId = movie.getStatus();
        int col,col2;
        if(colorId==1){
             col=R.color.cardyellow;
            col2 =R.color.bg_black;
        }else{
            col=R.color.card_yellowbg;
            col2 =R.color.calender_grey;
            holder.date.setTypeface(null, Typeface.BOLD);
            holder.mCardView.setCardElevation(0);

        }
        int color = holder.mCardView.getContext().getResources().getColor(col);
        int color2 = holder.mCardView.getContext().getResources().getColor(col2);
        holder.mCardView.setCardBackgroundColor(color);
        holder.date.setTextColor(color2);

//        holder.mCardView
        
    }

    @Override
    public int getItemCount() {
        return DateList.size();
    }
}