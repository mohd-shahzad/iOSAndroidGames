package com.example.mohdshahzad.daretomakegame;

import android.app.Activity;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        int i =2;

        int j = i++ + i++;

        System.out.print("sum"+j);


    }


}
