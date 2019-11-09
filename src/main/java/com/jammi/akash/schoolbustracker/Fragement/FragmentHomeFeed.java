package com.jammi.akash.schoolbustracker.Fragement;

import android.content.Context;

import android.net.ConnectivityManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jammi.akash.schoolbustracker.R;


public class FragmentHomeFeed extends Fragment {

    public static FragmentHomeFeed newInstance() {

        final FragmentHomeFeed fragment = new FragmentHomeFeed();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.fragmenthomefeed, parent, false);

        getIntents();
        initializeViews(view);
        setUpValues();
        setUpListeners();


        return view;
    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }






    public void getIntents() {
    }

    public void initializeViews(View view) {

    }
    public void setUpValues() {


    }

    public void setUpListeners() {



    }




}






