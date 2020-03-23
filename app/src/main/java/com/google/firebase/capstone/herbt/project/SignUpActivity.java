package com.google.firebase.capstone.herbt.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView showimage;
    private EditText finame, miname, laname, emailadd, pass;
    ProgressDialog dialog;

    private FirebaseAuth mAuth;
    private Uri imgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dialog = new ProgressDialog(SignUpActivity.this);
        finame = (EditText) findViewById(R.id.namef);
        miname = (EditText) findViewById(R.id.namem);
        laname = (EditText) findViewById(R.id.namel);
        emailadd = (EditText) findViewById(R.id.emailtext);
        pass = (EditText) findViewById(R.id.passwordtext);
        showimage = (ImageView)findViewById(R.id.imageView);

        mAuth = FirebaseAuth.getInstance();



        findViewById(R.id.buttonregister).setOnClickListener(this);
        findViewById(R.id.insign).setOnClickListener(this);
        findViewById(R.id.imageView).setOnClickListener(this);

    }
    public void Clearall() {
        finame.setText("");
        miname.setText("");
        laname.setText("");
        emailadd.setText("");
        pass.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

        }
    }



    private void registerUser() {

        final String firstName = finame.getText().toString().trim();
        final String middleName = miname.getText().toString().trim();
        final String lastName = laname.getText().toString().trim();
        final String email = emailadd.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (firstName.isEmpty()) {
            finame.setError("Name Required");
            finame.requestFocus();
            return;

        }
        if (middleName.isEmpty()) {
            miname.setError("Middle Name Required");
            miname.requestFocus();
            return;

        }
        if (lastName.isEmpty()) {
            laname.setError("LastName Required");
            laname.requestFocus();
            return;

        }
        if (email.isEmpty()) {
            emailadd.setError("Email Address Required");
            emailadd.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            pass.setError("Password Required");
            pass.requestFocus();
            return;

        }
        if (password.length() != 6) {
            pass.setError("input atleast 6 character");
            pass.requestFocus();
            return;
        }
        dialog.setMessage("Loading.......");
        dialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    User user = new User(firstName, middleName, lastName, email);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "User Register Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this, LetsGetsStarted.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Clearall();
                                startActivity(intent);

                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(SignUpActivity.this, "Email Already Register", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });

                } else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonregister:
                    registerUser();
                break;

            case R.id.insign:
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                break;


    }
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}