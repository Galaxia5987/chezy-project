package com.example.galacticmarket;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This object communicates with a remote mysql database.
 * The database contains 2 tables, The first one contains the login data of the users,
 * and the second contains the data for the shop profile.
 */
public class BackgroundPost extends AsyncTask<String, Void, String> {
    Context context;
    private AlertDialog alertDialog;
    private String result;

    BackgroundPost(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        //There are 4 urls depend on which action is intended at the remote mysql server.
        String login_url = "https://galacticmarket.000webhostapp.com/login.php";
        String delete_url = "https://galacticmarket.000webhostapp.com/profileDelete.php";
        String profile_url = "https://galacticmarket.000webhostapp.com/profileUpdate.php";
        String signup_url = "https://galacticmarket.000webhostapp.com/insertion.php";
        String post_data = null;
        String type = params[0]; // type define which action is intended.
        /*
         * l - login
         * s - sign up
         * pd - delete the profile
         */

        switch (type) {
            case "l":
                try {
                    url = new URL(login_url);
                    post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                } catch (MalformedURLException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "s":
                try {
                    url = new URL(signup_url);
                    post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                } catch (MalformedURLException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "pd":
                try {
                    url = new URL(delete_url);
                    post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                } catch (MalformedURLException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //update profile
                try {
                    url = new URL(profile_url);
                    post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("loc", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                            URLEncoder.encode("link", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                            URLEncoder.encode("des", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
                } catch (MalformedURLException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
        }
        try {

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO_8859_1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
