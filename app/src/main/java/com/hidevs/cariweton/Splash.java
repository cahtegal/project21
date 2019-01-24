package com.hidevs.cariweton;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        FirebaseApp.initializeApp(this);

        ImageView imgLogo = findViewById(R.id.imgLogo);
        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.open_scale);
        imgLogo.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(Splash.this,MenuUtama.class));
            }

            private void finish() {
                //TODO Auto-generated method stub
            }
        }, 3000);
    }
}
