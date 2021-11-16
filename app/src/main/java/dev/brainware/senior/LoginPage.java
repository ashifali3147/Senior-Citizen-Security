package dev.brainware.senior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    Button btnLogin;
    TextView tvRegister;
    EditText tvEmail, tvPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();
        btnLogin = (Button) (findViewById(R.id.btnLogin));
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvEmail= (EditText) findViewById(R.id.tvEmail);
        tvPassword= (EditText) findViewById(R.id.tvPassword);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginPage.this, "Login", Toast.LENGTH_SHORT).show();
                performLogin();
//                startActivity(new Intent(LoginPage.this, Home.class));
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginPage.this, "Register", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginPage.this, Register.class));
            }
        });
    }
    private void performLogin() {
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            tvEmail.setError("Enter correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            tvPassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginPage.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(LoginPage.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}