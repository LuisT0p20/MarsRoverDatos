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
    ImageButton btnBackMenu;
    Spinner spnLanguaje,spnTextSize;
    Switch swModoOscuro;
    Button btnSaveSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnBackMenu = findViewById(R.id.btnImageBackSettings);
        btnBackMenu.setOnClickListener(this);
        spnLanguaje = findViewById(R.id.spnLanguaje);
        spnTextSize = findViewById(R.id.spnTextSize);
        swModoOscuro = findViewById(R.id.switchDark);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"English", "Español"});

    // Configura el diseño del dropdown del spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Asigna el adaptador al spinner
        spnLanguaje.setAdapter(adapter);

        spnLanguaje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Aquí puedes realizar acciones al seleccionar un idioma
                selectedLanguage = parent.getItemAtPosition(position).toString();
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
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", language);
        editor.apply();

        // Reiniciar la aplicación
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}