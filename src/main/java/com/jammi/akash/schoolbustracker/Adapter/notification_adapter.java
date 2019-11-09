package com.jammi.akash.schoolbustracker.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jammi.akash.schoolbustracker.CustomClass.notification_modelclass;
import com.jammi.akash.schoolbustracker.R;

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.ViewHolder>{
    private notification_modelclass[] listdata;

    // RecyclerView recyclerView;  
    public notification_adapter(notification_modelclass[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.notification_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final notification_modelclass notification_modelclass = listdata[position];
        holder.noty_head.setText(listdata[position].getNoti_head());
        holder.noty_sub.setText(listdata[position].getNoti_sub_noti_());
        holder.noty_time.setText(listdata[position].getTime());

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+notification_modelclass.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView noty_head,noty_sub,noty_time;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.noty_head = (TextView) itemView.findViewById(R.id.noti_main);
            this.noty_sub = (TextView) itemView.findViewById(R.id.noti_sub);
            this.noty_time = (TextView) itemView.findViewById(R.id.noti_time);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}  
