package com.example.messengerlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerlite.R;
import com.example.messengerlite.pojo.PhotosMessage;
import com.example.messengerlite.ui.activities.PhotoActivity;

import static com.example.messengerlite.ui.activities.Launcher.StringToBitMap;

public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<PhotosMessage> photos;
    PhotosMessage currentphoto;


    public PhotosAdapter(@NonNull Context context, @NonNull List<PhotosMessage> photos) {

        this.context = context;
        this.photos = photos;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos , parent  ,false);
        return new photosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        currentphoto = photos.get(position);
        final photosHolder photos_Holder = (photosHolder) holder;
        photos_Holder.photo.setImageBitmap(StringToBitMap(currentphoto.getPhoto()));
        photos_Holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO photo activity
                Intent photoActivity = new Intent(context, PhotoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("photo",currentphoto.getPhoto());
                photoActivity.putExtras(bundle);
                context.startActivity(photoActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class photosHolder extends RecyclerView.ViewHolder {
        ImageView photo ;
        public photosHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.Image);
        }
    }

}
