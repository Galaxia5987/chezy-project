package com.example.galacticmarket;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundGet extends AsyncTask<String, Void, String> {
    Context context;
    private String result;

    BackgroundGet(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String site_url = "https://galacticmarket.000webhostapp.com/profileGet.php";


        try {
            //creating a URL
            URL url = new URL(site_url);

            //Opening the URL using HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //StringBuilder object to read the string from the service
            StringBuilder sb = new StringBuilder();

            //We will use a buffered reader to read the string from service
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            //A simple string to read values from each line
            String json;

            //reading until we don't find null
            while ((json = bufferedReader.readLine()) != null) {

                //appending it to string builder
                sb.append(json + "\n");
            }

            //finally returning the read string
            return sb.toString().trim();
        } catch (Exception e) {
            return null;
        }

    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
