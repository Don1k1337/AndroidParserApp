package com.example.androidparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button pullTxt, PullImg, goToSite, pullData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullTxt = findViewById(R.id.pullTxtBtn);
        txt = findViewById(R.id.textview);


        pullTxt.setOnClickListener(v -> new Textparse().execute());

        goToSite = findViewById(R.id.webBtn);
        goToSite.setOnClickListener(v -> {
            Intent gotoWebSite = new Intent(getApplicationContext(), WebViewParser.class);
            startActivity(gotoWebSite);
        });

        PullImg = findViewById(R.id.pullimgBtn);
        PullImg.setOnClickListener(v -> {
            Intent gotoImageParse = new Intent(getApplicationContext(), ImageParser.class);
            startActivity(gotoImageParse);
        });

        pullData = findViewById(R.id.pullBtn);
        pullData.setOnClickListener(v -> new DataParse().execute());

    }

    public class DataParse extends AsyncTask<Void, Void, Void> {
        String words;


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://iuca.kg/en/").timeout(6000).get();
                Elements header = doc.select("h2");
                words = header.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txt.setText(words);
        }
    }

    public class Textparse extends AsyncTask<Void, Void, Void> {
        String words;


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://iuca.kg/en/").timeout(6000).get();
                Elements title = doc.select("#9229b132");
                System.out.println(title.text());
                words = doc.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txt.setText(words);
        }
    }


}