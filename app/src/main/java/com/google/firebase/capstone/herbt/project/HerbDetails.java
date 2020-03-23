package com.google.firebase.capstone.herbt.project;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class HerbDetails extends AppCompatActivity {

    TextView mBisnames,mEngnames,mHerbcount;
    ImageView mImageIvs;

     private FirebaseAuth auth;
      private FirebaseUser user;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herb_details);

        mBisnames = (TextView)findViewById(R.id.bisayatextview);
        mEngnames = (TextView)findViewById(R.id.englishnametextview);
        mImageIvs = (ImageView)findViewById(R.id.imageView3);
        mHerbcount = (TextView)findViewById(R.id.familytextview);
       // FirebaseDatabase  database = FirebaseDatabase.getInstance();
      //  DatabaseReference mDatabaseRef = database.getReference();


    //    String id = getIntent().getStringExtra("USERID");
        String image = getIntent().getStringExtra("IMAGE");
        String bisname = getIntent().getStringExtra("BISNAME");
        String engname = getIntent().getStringExtra("ENGNAME");
        String herbcount = getIntent().getStringExtra("HERBCOUNT");



     //   mDatabaseRef.child("Herbal").child(id).child("HerbCount").setValue(herbcount);

        mHerbcount.setText(herbcount);
        mBisnames.setText(bisname);
        mEngnames.setText(engname);
        Picasso.get().load(image).into(mImageIvs);

    }
}
