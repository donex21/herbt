package com.google.firebase.capstone.herbt.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "Bisname";
    public static final String EXTRA_ENGNAME = "Engname";
    public static final String EXTRA_FAMILYNAME = "Family";
    public static final String EXTRA_LOCATIONNAME = "Location";
    public static final String EXTRA_SCIENAME = "Sciename";
    public static final String EXTRA_STATUSNAME = "Status";
    public static final String EXTRA_TREATMENTSNAME = "Treatments";
    public static final String EXTRA_TYPENAME = "Type";
    public static final String EXTRA_PROCEDURE = "Procedure";


    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private SearchView searchButton;

    private ProgressBar progressBar;

    private DatabaseReference mDatabaseRef;
    private List<Display> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mRecyclerView=findViewById(R.id.recycler_view);
       // searchButton = (SearchView)findViewById(R.id.imageButton);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar=findViewById(R.id.progress_circular);

        mDatabaseRef=  FirebaseDatabase.getInstance().getReference("Herbal");

       /* searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ViewActivity.this, FilterSearchActivity.class);
                //startActivity(intent);
            }
        });*/


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads=new ArrayList<Display>(); 
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    /*Display p =postSnapshot.getValue(Display.class);
                    Display display =new Display();
                    String Bisname = p.getBisname();
                    String Engname = p.getEngname();
                    String Family = p.getFamily();
                    String Location = p.getLocation();
                    String Sciename = p.getSciename();
                    String Status = p.getStatus();
                    String Treatments = p.getTreatments();
                    String Type = p.getType();
                    String Procedure = p.getProcedure();
                    String ImageUrl =p.getImageUrl();

                    display.setBisname(Bisname);
                    display.setEngname(Engname);
                    display.setFamily(Family);
                    display.setLocation(Location);
                    display.setSciename(Sciename);
                    display.setStatus(Status);
                    display.setTreatments(Treatments);
                    display.setType(Type);
                    display.setProcedure(Procedure);
                    display.setImageUrl(ImageUrl);
                    mUploads.add(display);*/
                }
                mAdapter=new ImageAdapter(ViewActivity.this,(ArrayList<Display>) mUploads);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(ViewActivity.this,3);
                mRecyclerView.setLayoutManager(recyce);
                mRecyclerView.setItemAnimator( new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItAemClickListener(ViewActivity.this);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,HerbsDetailsActivity.class);
        Display clickedItem = mUploads.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getBisname());
        detailIntent.putExtra(EXTRA_ENGNAME,clickedItem.getEngname());
        detailIntent.putExtra(EXTRA_FAMILYNAME,clickedItem.getFamily());
        detailIntent.putExtra(EXTRA_LOCATIONNAME,clickedItem.getLocation());
        detailIntent.putExtra(EXTRA_SCIENAME,clickedItem.getSciename());
        detailIntent.putExtra(EXTRA_STATUSNAME,clickedItem.getStatus());
        detailIntent.putExtra(EXTRA_TREATMENTSNAME,clickedItem.getTreatments());
        detailIntent.putExtra(EXTRA_TYPENAME,clickedItem.getType());
        detailIntent.putExtra(EXTRA_PROCEDURE,clickedItem.getProcedure());
        startActivity(detailIntent);
    }
   /* private void firebaseSearch(String searchText){

        Query firebaseSearchQuery = mDatabaseRef.orderByChild("Bisname").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Display,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Display, ViewHolder>(Display.class,
                        R.layout.image_item,
                        ViewHolder.class,
                        firebaseSearchQuery) {
                    @Override
                    protected void populateViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Display display) {

                    }

                };

    }*/
}
