package com.google.firebase.capstone.herbt.project;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

        //item click
      itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mClickListener.onItemClick(view,getAdapterPosition());
          }
      });
        // item long click
    itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            mClickListener.onItemLongClick(view,getAdapterPosition());
            return true;
        }
    });

    }

    public void setDetails(Context ctx,String bisname,String engname,String image){

        TextView mTitleView = mView.findViewById(R.id.img_description);
        ImageView mImageIv = mView.findViewById(R.id.image_view);

        mTitleView.setText(bisname);
        Picasso.get().load(image).into(mImageIv);
    }
    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
