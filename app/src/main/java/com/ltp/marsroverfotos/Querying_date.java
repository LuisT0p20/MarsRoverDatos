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

public class Querying_date extends AppCompatActivity {

    private EditText editTextDate,editTextPage;
    private Spinner spnCamera;
    private Button buttonSearch;
    private ImageButton backMenu;
    private TextView descripcionCamera;
    private RecyclerView recyclerViewPhotos;
    private PhotoAdapter photoAdapter;
    private List<Photo> photoList;


    private static final String NASA_API_KEY = "KldB9Vs3yG5KAod8Euqigyw8Bky4HaMd2UMmV49w";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querying_date);

        editTextDate = findViewById(R.id.editTextDate);
        spnCamera = findViewById(R.id.spnCameraDate);
        editTextPage = findViewById(R.id.editTextPage);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos);
        descripcionCamera = findViewById(R.id.descripcionCameraDate);
        backMenu = findViewById(R.id.QueryingDateBackMenu);

        photoList = new ArrayList<>();//creacion de instancia ArrayList que se llamra photoList que es una lista de tipo photo
        photoAdapter = new PhotoAdapter(photoList);//instancia photoAdapter que pasa como constructor a photoList

        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPhotos.setAdapter(photoAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String camera = spnCamera.getSelectedItem().toString();
                String page = editTextPage.getText().toString();

                searchPhotos(date, camera, page);
            }
        });
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Querying_date.this,Menu.class);
                startActivity(intent);
            }
        });
        spnCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void searchPhotos(String date, String camera, String page) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=" + date +
                "&camera=" + camera +
                "&page=" + page +
                "&api_key=" + NASA_API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);//instancia que utiliza el metodo newRequestQueue, recibe como argumento el contexto
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,//realiza la solicitud y recibe una respuesta en formato json
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            photoList.clear();//limpiar lista para evitar datos duplicados

                            JSONArray photosArray = response.getJSONArray("photos");//se obtiene un objeto json llamdo photos
                            for (int i = 0; i < photosArray.length(); i++) {
                                JSONObject photoObject = photosArray.getJSONObject(i);
                                String imageUrl = photoObject.getString("img_src");
                                String date = photoObject.getString("earth_date");
                                String name = photoObject.getJSONObject("rover").getString("name");
                                String launchDate = photoObject.getJSONObject("rover").getString("launch_date");
                                String arrivalDate = photoObject.getJSONObject("rover").getString("landing_date");
                                String state = photoObject.getJSONObject("rover").getString("status");

                                Photo photo = new Photo(imageUrl, date, name, launchDate, arrivalDate, state);
                                photoList.add(photo);
                            }

                            photoAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Querying_date.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

}
