package com.google.firebase.capstone.herbt.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_ENGNAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_FAMILYNAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_LOCATIONNAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_NAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_PROCEDURE;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_SCIENAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_STATUSNAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_TREATMENTSNAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_TYPENAME;
import static com.google.firebase.capstone.herbt.project.ViewActivity.EXTRA_URL;

public class HerbsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbs_details);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String Bisname = intent.getStringExtra(EXTRA_NAME);
        String Engname = intent.getStringExtra(EXTRA_ENGNAME);
        String Family = intent.getStringExtra(EXTRA_FAMILYNAME);
        String Location = intent.getStringExtra(EXTRA_LOCATIONNAME);
        String Sciename = intent.getStringExtra(EXTRA_SCIENAME);
        String Status = intent.getStringExtra(EXTRA_STATUSNAME);
        String Treatments = intent.getStringExtra(EXTRA_TREATMENTSNAME);
        String Type = intent.getStringExtra(EXTRA_TYPENAME);
        String Procedure = intent.getStringExtra(EXTRA_PROCEDURE);


        ImageView imageView = findViewById(R.id.imageView3);
        TextView textViewBisname = findViewById(R.id.bisayatextview);
        TextView textViewEngname = findViewById(R.id.englishnametextview);
        TextView textViewFamilyname = findViewById(R.id.familytextview);
        TextView textViewLocationname = findViewById(R.id.locationtextview);
        TextView textViewSciename = findViewById(R.id.sciencenametextview);
        TextView textViewStatusname = findViewById(R.id.statustextview);
        TextView textViewTreatmentsname = findViewById(R.id.treatmentstextview);
        TextView textViewTypename = findViewById(R.id.typetextview);
        TextView textViewProcedure = findViewById(R.id.proceduretextview);
      //  textViewProcedure.setMovementMethod((MovementMethod) textViewProcedure);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewBisname.setText(Bisname);
        textViewEngname.setText(Engname);
        textViewFamilyname.setText(Family);
        textViewLocationname.setText(Location);
        textViewSciename.setText(Sciename);
        textViewStatusname.setText(Status);
        textViewTreatmentsname.setText(Treatments);
        textViewTypename.setText(Type);
        textViewProcedure.setText(Procedure);

    }
}
