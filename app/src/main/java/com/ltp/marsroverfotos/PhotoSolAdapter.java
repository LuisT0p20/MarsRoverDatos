package com.ltp.marsroverfotos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoSolAdapter extends RecyclerView.Adapter<PhotoSolAdapter.PhotoViewHolder> {

    private List<PhotoSol> photoSolList;

    public PhotoSolAdapter(List<PhotoSol> photoSolList) {
        this.photoSolList = photoSolList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_date, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoSol photoSol = photoSolList.get(position);

        Picasso.get()
                .load(photoSol.getImageUrl())
                .into(holder.imageViewPhoto);

        holder.textViewSol.setText("Sol: " + photoSol.getSol());
        holder.textViewName.setText("Name: " + photoSol.getName());
        holder.textViewLaunchDate.setText("Launch Date: " + photoSol.getLaunchDate());
        holder.textViewArrivalDate.setText("Arrival Date: " + photoSol.getArrivalDate());
        holder.textViewState.setText("State: " + photoSol.getState());
    }

    @Override
    public int getItemCount() {
        return photoSolList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPhoto;
        public TextView textViewSol;
        public TextView textViewName;
        public TextView textViewLaunchDate;
        public TextView textViewArrivalDate;
        public TextView textViewState;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewSol = itemView.findViewById(R.id.textViewDate);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLaunchDate = itemView.findViewById(R.id.textViewLaunchDate);
            textViewArrivalDate = itemView.findViewById(R.id.textViewArrivalDate);
            textViewState = itemView.findViewById(R.id.textViewState);
        }
    }
}
