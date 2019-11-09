package com.jammi.akash.schoolbustracker.CustomClass;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.jammi.akash.schoolbustracker.Activity.parentmapActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

public class BackgroundTask extends AsyncTask<String,Void,String>{
    public int bus_no;
    AlertDialog alertDialog;
    Context ctx;
    String json_url;
    Double lat,log;
    JSONArray studentdetails = null;
    String name;

    public BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
         json_url = "http://bustracker111111.000webhostapp.com/json_get_data.php";
        alertDialog = new AlertDialog.Builder(ctx).create();
//        alertDialog.setTitle("Login Error");
    }
    @Override
    protected String doInBackground(String... params) {

        String login_url = "http://bustracker111111.000webhostapp.com/login.php";
        String busloc_url = "http://bustracker111111.000webhostapp.com/busloc.php";



        SharedPreferences pref = ctx.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl(json_url);

            try {
                studentdetails = json.getJSONArray("server_response");
                for(int i = 0; i < studentdetails.length(); i++){
                    JSONObject c = studentdetails.getJSONObject(i);
                    // Storing each json item in variable
                         bus_no = Integer.parseInt(c.getString("busno"));
                         editor.putInt("busno",bus_no);
                            lat = Double.valueOf(c.getString("xpickup"));
                            editor.putString("lat",""+lat);
                            log = Double.valueOf(c.getString("ypickup"));
                            editor.putString("log",""+log);
                            name=c.getString("name");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        {
                   try {
            String login_name = params[1];
            String login_pass = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                Log.e("response above while",""+response);

                while ((line = bufferedReader.readLine())!=null)
                {
                    Log.e("response came inside",""+response);
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.e("response",""+response);
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
//int rel = 7;
Log.e("response_length",""+result.length());
        if(result.length()==7)
        {  Log.e("response","Login verified true");
            PreferenceData.setUserLoggedInStatus(ctx,true);
            PreferenceData.setLoggedInUserEmail(ctx,name);
            PreferenceData.setlatandLog(ctx,lat,log);
            Intent a = new Intent(ctx, parentmapActivity.class);
            a.putExtra("busno",bus_no);
            a.putExtra("lat",lat);
            a.putExtra("log",log);

            ctx.startActivity(a);
            ((Activity)ctx).finish();
        }
        else
        {
            alertDialog.setMessage(""+result);
            alertDialog.show();
        }
    }
}
