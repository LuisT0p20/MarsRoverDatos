package com.ltp.marsroverfotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private String selectedLanguage;
    private int selectedTextSize; // Variable para almacenar el tamaño de texto seleccionado

    ImageButton btnBackMenu;
    Spinner spnLanguage, spnTextSize;
    Switch swModoOscuro;
    Button btnSaveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnBackMenu = findViewById(R.id.btnImageBackSettings);
        btnBackMenu.setOnClickListener(this);

        spnLanguage = findViewById(R.id.spnLanguage);
        spnTextSize = findViewById(R.id.spnTextSize);
        swModoOscuro = findViewById(R.id.switchDark);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"English", "Español"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLanguage.setAdapter(adapter);

        ArrayAdapter<String> textSizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"15", "18", "20"});
        textSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTextSize.setAdapter(textSizeAdapter);

        spnLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnTextSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                selectedTextSize = Integer.parseInt(selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(selectedLanguage)) {
                    changeLanguage(selectedLanguage);
                }
            }
        });
    }

    private void changeLanguage(String language) {
        // Guardar el idioma seleccionado en las preferencias compartidas
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", language);
        editor.apply();

        // Cambiar el idioma en tiempo de ejecución
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        // Reiniciar la actividad actual
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
