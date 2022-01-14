package dev.brainware.senior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
LottieAnimationView lotte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        lotte.animate();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences pref = getSharedPreferences("dev.brainware.senior.logdetails", Activity.MODE_PRIVATE);
                boolean isLogin = pref.getBoolean("isLogin",false);
                if(isLogin) {
                    Intent loginPage = new Intent(MainActivity.this, Home.class);
                    startActivity(loginPage);
                }
                else{
                    Intent registerPage = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(registerPage);
                }
                // close this activity
                finish();
            }
        }, 3000);
    }
}