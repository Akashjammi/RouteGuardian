package com.jammi.akash.schoolbustracker.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jammi.akash.schoolbustracker.Adapter.ViewPagerAdapter;
import com.jammi.akash.schoolbustracker.CustomClass.FetchURL;
import com.jammi.akash.schoolbustracker.CustomClass.JSONParser;
import com.jammi.akash.schoolbustracker.CustomClass.NavigationModel;
import com.jammi.akash.schoolbustracker.CustomClass.PreferenceData;
import com.jammi.akash.schoolbustracker.R;
import com.jammi.akash.schoolbustracker.Interface.TaskLoadedCallback;
import android.location.Location;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class parentmapActivity  extends AppCompatActivity  implements OnMapReadyCallback, TaskLoadedCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    Marker marker;
    private MarkerOptions place1, place2,place3;
    Button getDirection;
    Polyline currentPolyline;
    Handler handler = new Handler();
    private static String url = "http://bustracker111111.000webhostapp.com/bus_info_json.php";
    private static final String TAG_BUSNO = "busno";
    private static final String TAG_LAT = "lat";
    private static final String TAG_LONG = "long";
    private static final String TAG_DRIVER = "driver";
    private static final String TAG_NUMB = "numberplate";
    private static final String TAG_COUNT = "stud_count";
    String driverlat,driverlong,drivername;
    JSONArray busdetails = null;
    ListView mDrawerList;
    LayoutInflater inflater;
    DrawerLayout mDrawerLayout;
    DrawerAdapter navDrawerAdapter;
    Double picklat,picklog;
    CardView busstatus;
    LinearLayout busstatus_icv;
    TextView name_tv,class_Sec_tv;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_parentmap);
        init();
        adapter();
    }

    private void adapter() {
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notify = new Intent(getApplicationContext(),menu_Functions.class);
                notify.putExtra("option_no",3);
                startActivity(notify);


            }
        });


        busstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(busstatus_icv.getVisibility()== View.VISIBLE){
                    busstatus_icv.setVisibility(View.GONE);

                }else{

                    busstatus_icv.setVisibility(View.VISIBLE);
                }
            }
        });
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("getdirection","clicked");
                new FetchURL(parentmapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            }
        });
        inflater = getLayoutInflater();
        (findViewById(R.id.sliding_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerContral();
            }
        });

    }

    private void init() {
        notification=(ImageView)findViewById(R.id.notification_btn);
        name_tv=(TextView)findViewById(R.id.login_name);
        name_tv.setText( PreferenceData.getLoggedInEmailUser(getApplicationContext()));
        class_Sec_tv=(TextView)findViewById(R.id.class_section);

        getDirection = (Button) findViewById(R.id.btnGetDirection);

        busstatus=(CardView) findViewById(R.id.card_studentinfo);
        busstatus_icv=(LinearLayout)findViewById(R.id.add_detailscard);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        driverlat= PreferenceData.getDriverLat(getApplicationContext());
        driverlong=PreferenceData.getDriverLog(getApplicationContext());
        navDrawerAdapter = new DrawerAdapter(parentmapActivity.this, getNavigationModelList());
        mDrawerList.setAdapter(navDrawerAdapter);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        picklat=Double.parseDouble(PreferenceData.getLat(getApplicationContext()));
        picklog=Double.parseDouble(PreferenceData.getLog(getApplicationContext()));
        Log.e("pi",picklat + "++" + picklog);
        place1 = new MarkerOptions().position(new LatLng(picklat,picklog)).title("Home Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.homeptr));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //tab fragment


        viewPager = (ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    public void drawerContral(){

            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        }


    private ArrayList<NavigationModel> getNavigationModelList() {
        ArrayList<NavigationModel> navigationModels = new ArrayList<NavigationModel>();
        navigationModels.add(new NavigationModel( "HOME"));
        navigationModels.add(new NavigationModel( "PROFILE"));
        navigationModels.add(new NavigationModel( "EDIT LOCATION "));
        navigationModels.add(new NavigationModel( "SETTINGS"));
        navigationModels.add(new NavigationModel( "CONTACT US"));
//        navigationModels.add(new NavigationModel( "LOG OUT"));
       return navigationModels;
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
            String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
            String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
            String mode = "mode=" + directionMode;
            String parameters = str_origin + "&" + str_dest + "&" + mode;
            String output = "json";
            String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
             Log.e("url",url);
        return url;
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {



        try{
         if(isNetworkConnected()){
        mMap = googleMap;
        mMap.addMarker(place1);
        getdetailsbyparsing();
        place2 = new MarkerOptions().position(new LatLng(Double.valueOf(driverlat), Double.valueOf(driverlong))).title("\t with the"+"\t Bus is here!").icon(BitmapDescriptorFactory.fromResource(R.drawable.bus));
//        marker = mMap.addMarker(place2);

        getDirection.performClick();
        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        enableMyLocationIfPermitted();
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setMinZoomPreference(12);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                        onMapReady(googleMap);

                }
            }, 100000);

       }
        else{
             new AlertDialog.Builder(getApplicationContext())
                     .setTitle("No Internet Connectivity")
                     .setMessage("This app requires internet connectivity to track location")
                     .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {

                         }
                     })
                      .show();

         }
        }

       catch (Exception e){
       Log.e("map error",e+"");

       }}



    private void getdetailsbyparsing() {


        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(url);

        try {
            busdetails = json.getJSONArray("server_response");
            for(int i = 0; i < busdetails.length(); i++){
                JSONObject c = busdetails.getJSONObject(i);
                if(Integer.parseInt(c.getString(TAG_BUSNO))== getIntent().getIntExtra("busno",0)) {// Storing each json item in variable
                    String busno = c.getString(TAG_BUSNO);
                    String studcount = c.getString(TAG_COUNT);
                    drivername = c.getString(TAG_DRIVER);
                    String lat = c.getString(TAG_LAT);
                    String longi = c.getString(TAG_LONG);
                    String number = c.getString(TAG_NUMB);
                    driverlat = lat;
                    driverlong = longi;
                    PreferenceData.setdriverlatandLog(getApplicationContext(),Double.parseDouble(driverlat),Double.parseDouble(driverlong));
                    Log.e("jsonvslues", "lat : " + lat + " long :" + longi);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }




    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(47.6739881, -122.121512);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(12);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {
                    mMap.setMinZoomPreference(12);
                }
            };

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        if(marker!=null)
            marker.remove();

        currentPolyline= mMap.addPolyline((PolylineOptions) values[0]);
        currentPolyline.setColor(R.color.bg_red);
        marker =mMap.addMarker(place2);

    }
    class DrawerAdapter extends BaseAdapter {
        Context context;
        ArrayList<NavigationModel> naviModels;

        public DrawerAdapter(Context context, ArrayList<NavigationModel> naviModelArrayList) {
            this.context = context;
            this.naviModels = naviModelArrayList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            Log.e("getView: ", position + "====");
            if (position == 0) {
                convertView = inflater.inflate(R.layout.item_header_navigation, null);

            } else {

                final NavigationModel navigationModel = naviModels.get(position - 1);
                convertView = inflater.inflate(R.layout.item_content_navi, null);
                TextView nav_title_tv = (TextView) convertView.findViewById(R.id.nav_title_tv);
                LinearLayout divider_ly = (LinearLayout) convertView.findViewById(R.id.divider_ly);
                if (navigationModel.nav_title.equals("Return Product") || navigationModel.nav_title.equals("Sign Out")) {
                    divider_ly.setVisibility(View.VISIBLE);
                } else {
                    divider_ly.setVisibility(View.GONE);
                }

                nav_title_tv.setText(navigationModel.nav_title);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("onClick: ", navigationModel.nav_title + "===" + position);
                        sideNavProceed(position);
                    }
                });
            }

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public int getCount() {
            return naviModels.size() + 1;
        }
    }
    public void sideNavProceed(int sideposition) {
        switch (sideposition) {

            case 1:

                break;
            case 2:
                Intent profile = new Intent(getApplicationContext(),menu_Functions.class);
                profile.putExtra("option_no",2);
                startActivity(profile);

                break;
            case 3:
                  break;
            case 4:
                Intent settings = new Intent(getApplicationContext(),menu_Functions.class);
                settings.putExtra("option_no",1);
                startActivity(settings);
                break;
            case 5:
                 break;
            case 6:
                PreferenceData.setUserLoggedInStatus(getApplicationContext(),false);
                Intent logout = new Intent(parentmapActivity.this,MainActivity.class);
                startActivity(logout);

                break;



        }


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}