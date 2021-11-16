package dev.brainware.senior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    ImageView imgBmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgBmi = (ImageView) findViewById(R.id.imgBmi);
        imgBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "BMI", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, BmiHome.class));
            }
        });
    }
}