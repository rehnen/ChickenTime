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
import java.util.Calendar;
import java.util.Locale;

import se.marcusrehn.chickentime.logic.FoodProcessor;

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
        String message = FoodProcessor.NO_CHICKEN;

        String special = FoodProcessor.getWeeklyFoodMessage(s);

        String day = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
        String foodOfTheDay = FoodProcessor.getFoodMessageForDay(s, day);

        if(foodOfTheDay.contains(FoodProcessor.CHICKEN_DAY) || foodOfTheDay.equals(FoodProcessor.AIOLI)) {
            message = foodOfTheDay;
        } else if(special.equals(FoodProcessor.CHICKEN_WEEK)) {
            message = special;
        }

        Toast.makeText(this.context,
                message,
                Toast.LENGTH_LONG).show();
        super.onPostExecute(s);
    }
}
