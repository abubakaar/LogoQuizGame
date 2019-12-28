package com.example.user.logoquizgame;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categories extends AppCompatActivity {




    ImageButton food,social,car,shopping;
    Button f,so,c,s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        food=(ImageButton) findViewById(R.id.Food);
        social=(ImageButton) findViewById(R.id.Social);
        car=(ImageButton) findViewById(R.id.Car);
        shopping=(ImageButton) findViewById(R.id.Shopping);
        f=(Button) findViewById(R.id.food);
        so=(Button) findViewById(R.id.social);
        c=(Button) findViewById(R.id.car);
        s=(Button) findViewById(R.id.shopping);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Food.class);
                startActivity(i);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Food.class);
                startActivity(i);
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Social.class);
                startActivity(i);
            }
        });
        so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Social.class);
                startActivity(i);
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Car.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Car.class);
                startActivity(i);
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Shopping.class);
                startActivity(i);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Shopping.class);
                startActivity(i);
            }
        });
    }
}
