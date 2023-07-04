package com.ltp.marsroverfotos;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Querying_sol extends AppCompatActivity {

    private EditText editTextSol,editTextPageSol;
    private Spinner spnCameraSol;
    private Button buttonSearch;
    private ImageButton backMenu;
    private TextView descripcionCamera;
    private RecyclerView recyclerViewPhotos;
    private PhotoSolAdapter photoSolAdapter;
    private List<PhotoSol> photoSolList;

    private static final String NASA_API_KEY = "KldB9Vs3yG5KAod8Euqigyw8Bky4HaMd2UMmV49w";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querying_sol);

        editTextSol = findViewById(R.id.editTextSol);
        spnCameraSol = findViewById(R.id.spnCameraSol);
        editTextPageSol = findViewById(R.id.editTextPageSol);
        buttonSearch = findViewById(R.id.buttonSearchSol);
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotosSol);
        backMenu = findViewById(R.id.QueryingSolBackMenu);
        descripcionCamera = findViewById(R.id.descripcionCameraSol);

        photoSolList = new ArrayList<>();
        photoSolAdapter = new PhotoSolAdapter(photoSolList);
        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPhotos.setAdapter(photoSolAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sol = editTextSol.getText().toString();
                String camera = spnCameraSol.getSelectedItem().toString();
                String page = editTextPageSol.getText().toString();

                searchPhotos(sol, camera, page);
            }
        });
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Querying_sol.this,Menu.class);
                startActivity(intent);
            }
        });
        spnCameraSol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = (String) parent.getItemAtPosition(position);
                switch (elementoSeleccionado){
                    case "FHAZ": descripcionCamera.setText("Front Hazard Avoidance Camera");break;
                    case "RHAZ": descripcionCamera.setText("Rear Hazard Avoidance Camera");break;
                    case "MAST": descripcionCamera.setText("Mast Camera");break;
                    case "CHEMCAM": descripcionCamera.setText("Chemistry and Camera Complex");break;
                    case "MAHLI": descripcionCamera.setText("Mars Hand Lens Imager");break;
                    case "MARDI": descripcionCamera.setText("Mars Descent Imager");break;
                    case "NAVCAM": descripcionCamera.setText("Navigation Camera");break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void searchPhotos(String sol, String camera, String page) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + sol +
                "&camera=" + camera +
                "&page=" + page +
                "&api_key=" + NASA_API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            photoSolList.clear();

                            JSONArray photosArray = response.getJSONArray("photos");
                            for (int i = 0; i < photosArray.length(); i++) {
                                JSONObject photoObject = photosArray.getJSONObject(i);
                                String imageUrl = photoObject.getString("img_src");
                                String sol = photoObject.getString("sol");
                                String name = photoObject.getJSONObject("rover").getString("name");
                                String launchDate = photoObject.getJSONObject("rover").getString("launch_date");
                                String arrivalDate = photoObject.getJSONObject("rover").getString("landing_date");
                                String state = photoObject.getJSONObject("rover").getString("status");

                                PhotoSol photoSol = new PhotoSol(imageUrl, sol, name, launchDate, arrivalDate, state);
                                photoSolList.add(photoSol);
                            }

                            photoSolAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Querying_sol.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }
}
