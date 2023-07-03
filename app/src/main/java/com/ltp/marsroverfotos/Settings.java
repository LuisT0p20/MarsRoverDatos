package com.ltp.marsroverfotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private String selectedLanguage;

    ImageButton btnBackMenu;
    Spinner spnLanguaje,spnTextSize;
    Switch swModoOscuro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnBackMenu = findViewById(R.id.btnImageBackSettings);
        btnBackMenu.setOnClickListener(this);
        spnLanguaje = findViewById(R.id.spnLanguaje);
        spnTextSize = findViewById(R.id.spnTextSize);
        swModoOscuro = findViewById(R.id.switchDark);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}