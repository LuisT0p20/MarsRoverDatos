package com.ltp.marsroverfotos;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    ImageButton btnSolMarciano,btnFechaTierra,btnFavoritos,btnSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnSolMarciano = findViewById(R.id.btnImgSolMarciano);
        btnFechaTierra = findViewById(R.id.btnImgFechaTierra);
        btnFavoritos = findViewById(R.id.btnImgFavoritos);
        btnSettings = findViewById(R.id.btnImgSettings);

        btnSolMarciano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Querying_sol.class);
                startActivity(intent);
            }
        });
        btnFechaTierra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Querying_date.class);
                startActivity(intent);
            }
        });
        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Favorites.class);
                startActivity(intent);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}