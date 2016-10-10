package se.marcusrehn.chickentime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    URL[] urls = new URL[1];
                    urls[0] = new URL("http://www.utkikenkarlskrona.se/ericsson/");
                    new ChickenGatherer(getApplicationContext()).execute(urls);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
