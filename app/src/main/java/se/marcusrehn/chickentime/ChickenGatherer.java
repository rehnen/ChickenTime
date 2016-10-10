package se.marcusrehn.chickentime;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by rehnen on 2016-10-09.
 */

public class ChickenGatherer extends AsyncTask<URL, Integer, String> {

    private final Context context;
    ChickenGatherer(Context c) {
        this.context = c;
    }
    @Override
    protected String doInBackground(URL... urls) {
        URL url = urls[0];
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


            return sb.toString();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        } finally {
            urlConnection.disconnect();
        }

        return "Unable to connect to Utkiken";
    }

    @Override
    protected void onPostExecute(String s) {
        String message = "Disapoint";

        String special = s.substring(s.indexOf("this week's special"), s.indexOf("<p><b>Monday</b><br />"));
        Calendar c = Calendar.getInstance();
        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);

        String todaydsFood = "";

        if(!day.equals("Saturday") && !day.equals("Sunday")) {
            todaydsFood = s.substring(s.indexOf("<p><b>"+ day +"</b><br />"));
            todaydsFood = todaydsFood.substring(0, todaydsFood.indexOf("</p>"));
        }

        if(special.toLowerCase().contains("chicken")) {

            message = "Chicken all week";

        } else if(todaydsFood.toLowerCase().contains("chicken")) {

            message = "The gods are smiling this " + day;

        } else {

            message = "No chicken today :(";
        }

        Toast.makeText(this.context,
                message,
                Toast.LENGTH_LONG).show();
        super.onPostExecute(s);
    }
}
