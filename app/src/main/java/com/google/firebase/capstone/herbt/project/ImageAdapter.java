package com.google.firebase.capstone.herbt.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private OnItemClickListener mListener;
    private Context mContext;
    private List<Display> mUploads;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItAemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ImageAdapter(Context context, ArrayList<Display> uploads)
    {
        mContext=context;
        mUploads=uploads;

    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item, parent,false);
        return  new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Display uploadCur=mUploads.get(position);
        holder.img_description.setText(uploadCur.getBisname());
        Picasso.get()
                .load(uploadCur.getImageUrl())
                .placeholder(R.drawable.imagepreview)
                .fit()
                .centerCrop()
                .into(holder.image_view);


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView img_description;
        public ImageView image_view;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img_description=itemView.findViewById(R.id.img_description);
            image_view=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

}
