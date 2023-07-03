package com.ltp.marsroverfotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    Context context;
    private List<Photo> photoList;

    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_date, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);

        // Descargar la imagen y guardarla en un archivo local
        Glide.with(holder.itemView.getContext())
                .load(photo.getImageUrl())
                .centerCrop()
                .error(R.drawable.planet)
                .into(holder.imageViewPhoto);

        holder.textViewDate.setText("Date: " + photo.getDate());
        holder.textViewName.setText("Name: " + photo.getName());
        holder.textViewLaunchDate.setText("Launch Date: " + photo.getLaunchDate());
        holder.textViewArrivalDate.setText("Arrival Date: " + photo.getArrivalDate());
        holder.textViewState.setText("State: " + photo.getState());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPhoto;
        public TextView textViewDate;
        public TextView textViewName;
        public TextView textViewLaunchDate;
        public TextView textViewArrivalDate;
        public TextView textViewState;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLaunchDate = itemView.findViewById(R.id.textViewLaunchDate);
            textViewArrivalDate = itemView.findViewById(R.id.textViewArrivalDate);
            textViewState = itemView.findViewById(R.id.textViewState);
        }
    }
}
