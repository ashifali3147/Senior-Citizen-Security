package dev.brainware.senior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    Button btnSignup;
    EditText tvName, tvEmail, tvPassword, tvCfPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        tvName= (EditText) findViewById(R.id.tvName);
        tvEmail= (EditText) findViewById(R.id.tvEmail);
        tvPassword= (EditText) findViewById(R.id.tvPassword);
        tvCfPassword= (EditText) findViewById(R.id.tvCfPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this, "Signup", Toast.LENGTH_SHORT).show();
                performAuth();
//                startActivity(new Intent(Register.this, LoginPage.class));
            }
        });
    }

    private void performAuth() {
        String name = tvName.getText().toString();
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();
        String confirmPassword = tvCfPassword.getText().toString();

        if(!email.matches(emailPattern)){
            tvEmail.setError("Enter correct Email");
        }
        else if(password.isEmpty() || password.length()<6){
            tvPassword.setError("Enter Proper Password");
        }
        else if (!password.matches(confirmPassword)){
            tvCfPassword.setError("Password not mach");
        }
        else{
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, LoginPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}