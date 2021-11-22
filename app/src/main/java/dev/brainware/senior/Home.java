package dev.brainware.senior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageView imgBmi;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences pref = getSharedPreferences("dev.brainware.senior.logdetails", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        imgBmi = (ImageView) findViewById(R.id.imgBmi);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        imgBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "BMI", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, BmiHome.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("isLogin", false);
                editor.apply();
                startActivity(new Intent(Home.this, LoginPage.class));
                finish();
            }
        });
    }
}