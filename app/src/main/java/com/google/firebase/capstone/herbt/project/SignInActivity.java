package com.google.firebase.capstone.herbt.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    TextView emTv, paTv;
    ProgressDialog dialog;
    ImageView eye;
    TextView sign_in_password;
    Button fbutton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        dialog = new ProgressDialog(SignInActivity.this);
        emTv = (TextView) findViewById(R.id.sign_in_email);
        paTv = (TextView) findViewById(R.id.sign_in_password);
        eye = (ImageView) findViewById(R.id.show_pass_btn);
        sign_in_password = (TextView) findViewById(R.id.sign_in_password);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.forgotButton).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
    }
    private void userLogin() {
        String email = emTv.getText().toString().trim();
        String password = paTv.getText().toString().trim();

        if (email.isEmpty()) {
            emTv.setError("Email is required");
            emTv.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emTv.setError("Please enter a valid email");
            emTv.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            paTv.setError("Password is required");
            paTv.requestFocus();
            return;
        }

        if (password.length() < 6) {
            paTv.setError("Minimum lenght of password should be 6");
            paTv.requestFocus();
            return;
        }
        dialog.setMessage("Loading.......");
        dialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(SignInActivity.this, LetsGetsStarted.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.sign_in_button:
                userLogin();
                break;
            case R.id.forgotButton:
                finish();
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
    }
}
    public void ShowHidePass(View view) {
        if (view.getId() == R.id.show_pass_btn) {
            if (sign_in_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye_hide_icon);

                //Show Password
                sign_in_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_eye_icon);

                //Hide Password
                sign_in_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}