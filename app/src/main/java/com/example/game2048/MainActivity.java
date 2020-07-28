package com.example.game2048;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonPlay, buttonhow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectView();
        listenButton();
    }

    private void connectView(){
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonhow = findViewById(R.id.buttonHow);
    }

    private void listenButton(){
        buttonPlay.setOnClickListener(this);
        buttonhow.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == buttonPlay){
//            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, GamePlay.class);
            startActivity(intent);

        }
        if(v == buttonhow){
           Intent intent = new Intent(MainActivity.this, Howtoplay.class);
           startActivity(intent);
        }
    }
}
