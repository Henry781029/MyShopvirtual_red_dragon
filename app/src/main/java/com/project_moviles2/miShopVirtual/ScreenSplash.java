package com.project_moviles2.miShopVirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ScreenSplash extends AppCompatActivity {

    TextView jtvmensaje3, jtvmensaje4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        getSupportActionBar().hide();

        jtvmensaje3 = findViewById(R.id.tvmensaje3);
        jtvmensaje4 = findViewById(R.id.tvmensaje4);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_arriba);
        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.opaco);
        Animation animation3= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.desplazamiento_abajo);

        jtvmensaje3.setAnimation(animation1);
        jtvmensaje4.setAnimation(animation3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        },4000);

    }
}