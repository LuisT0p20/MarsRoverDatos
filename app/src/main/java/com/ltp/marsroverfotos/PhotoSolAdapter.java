package com.ltp.marsroverfotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PhotoSolAdapter extends RecyclerView.Adapter<PhotoSolAdapter.PhotoViewHolder> {

    private List<PhotoSol> photoSolList;//declara una lista de objetos llamada PHOTOSOLLIST

    public PhotoSolAdapter(List<PhotoSol> photoSolList) {//sirve para acceder a esto por otros metodos de la clase
        this.photoSolList = photoSolList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_date, parent, false);
        return new PhotoViewHolder(itemView);//inflador de recyclerView
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {//
        PhotoSol photoSol = photoSolList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(photoSol.getImageUrl())
                .centerCrop()
                .error(R.drawable.planet)
                .into(holder.imageViewPhoto);

        holder.textViewSol.setText("Sol: " + photoSol.getSol());
        holder.textViewName.setText("Name: " + photoSol.getName());
        holder.textViewLaunchDate.setText("Launch Date: " + photoSol.getLaunchDate());
        holder.textViewArrivalDate.setText("Arrival Date: " + photoSol.getArrivalDate());
        holder.textViewState.setText("State: " + photoSol.getState());
    }

    private void notifyFavoritesChanged() {
        // Notificar el cambio en el ArrayList de favoritos
        notifyDataSetChanged();
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
