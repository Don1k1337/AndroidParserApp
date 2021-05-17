package com.example.androidparser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageParser extends AppCompatActivity {
    ImageView img;
    Bitmap btm;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_parser);
        img = (ImageView) (findViewById(R.id.imageView));
        new ImageParse().execute();
        btn = findViewById(R.id.backBtn);

        btn.setOnClickListener(v -> {
            Intent gotoImageParse = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gotoImageParse);
        });
    }


    public class ImageParse extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                btm = BitmapFactory.decodeStream((InputStream) new URL("http://iuca.kg/wp-content/uploads/2019/11/DSCN4881-730x350.jpg").getContent());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            img.setImageBitmap(btm);
        }
    }
}