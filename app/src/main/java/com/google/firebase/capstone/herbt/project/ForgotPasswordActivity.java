package com.google.firebase.capstone.herbt.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {


    EditText emailtext;
    Button sendButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailtext = (EditText) findViewById(R.id.editText);
        sendButton = (Button)findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailtext.getText().toString();

                if (userEmail.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please write your valid email", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "Please check your Email Account, If you want to reset your Password ...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this,SignInActivity.class));
                            }
                            else {

                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPasswordActivity.this, "Error Occured:"+message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}
