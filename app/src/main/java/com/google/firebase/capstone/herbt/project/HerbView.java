package com.google.firebase.capstone.herbt.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

@SuppressWarnings("ALL")
public class HerbView extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef,databaseReference;

    TextView herbcountclick;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;

    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private String currentUserId;
    private  int HerbItemCount = 0;
    private float HerbPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herb_view);

        herbcountclick = (TextView)findViewById(R.id.herbcountview);
        mRecyclerView = findViewById(R.id.recycler_viewss);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       RecyclerView.LayoutManager recyce = new GridLayoutManager(HerbView.this,2);
        mRecyclerView.setLayoutManager(recyce);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Herbal");
        auth = FirebaseAuth.getInstance();
        currentUserId= auth.getCurrentUser().getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Herbal");


        databaseReference.orderByChild("UserID")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()){
                            HerbItemCount = (int) dataSnapshot.getChildrenCount();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }




    private void firebaseSearch(String searctText){

        Query firebaseSearchQuery = mRef.orderByChild("Treatments").startAt(searctText).endAt(searctText+"\uf8ff");

        FirebaseRecyclerAdapter<Display,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Display, ViewHolder>(
                        Display.class,
                        R.layout.image_item,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Display display, int i) {
                        viewHolder.setDetails(getApplicationContext(),display.getBisname(),display.getEngname(),display.getImageUrl());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                FirebaseDatabase  database = FirebaseDatabase.getInstance();
                                DatabaseReference mDatabaseRef = database.getReference();

                                //get data from views
                                String mbisname = getItem(position).getBisname();
                                String mengname =getItem(position).getEngname();
                                String mImage = getItem(position).getImageUrl();
                                String mHerbcount = getItem(position).getHerbCount().toString();
                                String id = getRef(position).getKey();

                                int herbCountClick = Integer.parseInt(mHerbcount);
                                herbCountClick++;

                                String strI = String.valueOf(herbCountClick);

                                //pass this data to nav activity
                                Intent intent = new Intent(view.getContext(),HerbDetails.class);
                                //intent.putExtra("USERID",id);
                                intent.putExtra("HERBCOUNT",strI);
                                intent.putExtra("BISNAME",mbisname);
                                intent.putExtra("ENGNAME",mengname);
                                intent.putExtra("IMAGE",mImage);

                                mDatabaseRef.child("Herbal").child(id).child("HerbCount").setValue(strI);
                                mDatabaseRef.child("Herbal").child(id).child("UserID").setValue(id);


                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder ;
                    }

                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query SortingData = mRef.orderByChild("HerbPercent");
        FirebaseRecyclerAdapter<Display,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Display, ViewHolder>(
                        Display.class,
                        R.layout.image_item,
                        ViewHolder.class,
                        SortingData
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Display display, int i) {

                        viewHolder.setDetails(getApplicationContext(),display.getBisname(),display.getEngname(),display.getImageUrl());
                    }


                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                FirebaseDatabase  database = FirebaseDatabase.getInstance();
                                DatabaseReference mDatabaseRef = database.getReference();

                                //get data from views
                                String mbisname = getItem(position).getBisname();
                                String mengname =getItem(position).getEngname();
                                String mImage = getItem(position).getImageUrl();
                                String mHerbcount = getItem(position).getHerbCount().toString();
                                String id = getRef(position).getKey();

                                int herbCountClick = Integer.parseInt(mHerbcount);
                                herbCountClick++;


                                String strI = String.valueOf(herbCountClick);

                                //pass this data to nav activity
                                Intent intent = new Intent(view.getContext(),HerbDetails.class);
                                //intent.putExtra("USERID",id);
                                intent.putExtra("HERBCOUNT",strI);
                                intent.putExtra("BISNAME",mbisname);
                                intent.putExtra("ENGNAME",mengname);
                                intent.putExtra("IMAGE",mImage);

                                HerbPercent =((float)herbCountClick / HerbItemCount) * -1 ;

                              //  String x = String.valueOf(HerbPercent);

                                mDatabaseRef.child("Herbal").child(id).child("HerbCount").setValue(strI);
                                mDatabaseRef.child("Herbal").child(id).child("UserID").setValue(id);
                                mDatabaseRef.child("Herbal").child(id).child("HerbPercent").setValue(HerbPercent);


                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder ;
                    }
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item  = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search by Illness...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter as you type
                firebaseSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //this is for itemclick


}
