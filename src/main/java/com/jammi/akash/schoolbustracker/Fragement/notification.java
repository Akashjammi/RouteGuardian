package com.jammi.akash.schoolbustracker.Fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jammi.akash.schoolbustracker.Adapter.notification_adapter;
import com.jammi.akash.schoolbustracker.CustomClass.notification_modelclass;
import com.jammi.akash.schoolbustracker.R;

public class notification extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        notification_modelclass[] notification_modelclass = new notification_modelclass[] {
                new notification_modelclass("Pick up Remainder","School bus is about to arrive at pickup spot","7:50 Am"),
                new notification_modelclass("Pick up Remainder","School bus is about to arrive at pickup spot","7:50 Am"),
                new notification_modelclass("Pick up Remainder","School bus is about to arrive at pickup spot","7:50 Am"),
                new notification_modelclass("Pick up Remainder","School bus is about to arrive at pickup spot","7:50 Am"),
                new notification_modelclass("Pick up Remainder","School bus is about to arrive at pickup spot","7:50 Am"),


        };

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.notification_rv);
        notification_adapter adapter = new notification_adapter(notification_modelclass);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    } }
