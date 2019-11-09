package com.jammi.akash.schoolbustracker.Fragement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jammi.akash.schoolbustracker.CustomClass.CalenderAdapter;
import com.jammi.akash.schoolbustracker.Adapter.cal_rv_adapter;
import com.jammi.akash.schoolbustracker.CustomClass.JSONParser;
import com.jammi.akash.schoolbustracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Tab2fragment extends Fragment {

    int position,stud_id;
    private TextView month_name;
    private TextView year;
    HashMap<String,Boolean> attendanceRegister = new HashMap<String, Boolean>();
   JSONArray studentdetails;
    private List<CalenderAdapter> CalenderAdapterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private cal_rv_adapter mAdapter;
    Calendar mCalendar;

    String attendance_url ="http://bustracker111111.000webhostapp.com/attendance_json.php";
    JSONArray Attendancedetails = null;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);

        Tab2fragment tabFragment = new Tab2fragment();
        tabFragment.setArguments(bundle);
        return tabFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tab2, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.calender_view);

        mAdapter = new cal_rv_adapter(CalenderAdapterList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 7));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
        try {
            getCalenderAttendance();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    private void getCalenderAttendance() throws JSONException {
        SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(attendance_url);
        studentdetails = json.getJSONArray("server_response");

        try {
            Map<String, String> params = new HashMap<String, String>();
            try
            {
                JSONObject objects = studentdetails.getJSONObject(0);
                Iterator key = objects.keys();
                Iterator<?> keys = json.keys();

                while (keys.hasNext())
                {
                    String ikey = (String) keys.next();
                    String value = json.getString(ikey);
                    params.put(ikey, value);
//                    Log.e("params",key + ":" + value);
                }
                Log.e("date att",params.get("server_response")+"");
//                Log.e("date ",params+"");


            }
            catch (Exception xx)
            {
                xx.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    private void prepareData() {
        Date first = getFirstDateOfCurrentMonth();
        Log.e("date",""+first);
        Calendar c = Calendar.getInstance();
        c.setTime(first);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Log.e("day..",""+dayOfWeek);

        CalenderAdapter CalenderAdapter;
        CalenderAdapterList.add(new CalenderAdapter(  "MON",0));
        CalenderAdapterList.add(new CalenderAdapter(  "TUE",0));
        CalenderAdapterList.add(new CalenderAdapter(  "WED",0));
        CalenderAdapterList.add(new CalenderAdapter(  "THU",0));
        CalenderAdapterList.add(new CalenderAdapter(  "FRI",0));
        CalenderAdapterList.add(new CalenderAdapter(  "SAT",0));
        CalenderAdapterList.add(new CalenderAdapter(  "SUN",0));
        for(int i=0;i<dayOfWeek-2;i++) {
            CalenderAdapterList.add(new CalenderAdapter("", 2));
        }
        Calendar cal = Calendar.getInstance();
        int monthMaxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for(int i=0;i<monthMaxDays;i++) {
                CalenderAdapter = new CalenderAdapter(i+1 + "",1);
                CalenderAdapterList.add(CalenderAdapter);
            }



            mAdapter.notifyDataSetChanged();
        }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        month_name = (TextView) view.findViewById(R.id.month);
        year = (TextView) view.findViewById(R.id.year);

        mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        month_name.setText(month);
        int year_v = Calendar.getInstance().get(Calendar.YEAR);
        year.setText(""+year_v);


//
//        textView.setText("Fragment 2 is loaded huu" + (position + 1));

    }
}