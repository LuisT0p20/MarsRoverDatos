package com.ltp.marsroverfotos;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    private EditText editTextDate;
    private Spinner spnCamera;
    private EditText editTextPage;
    private Button buttonSearch;
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

        photoList = new ArrayList<>();
        photoAdapter = new PhotoAdapter(photoList);
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
    }

    private void searchPhotos(String date, String camera, String page) {
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=" + date +
                "&camera=" + camera +
                "&page=" + page +
                "&api_key=" + NASA_API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            photoList.clear();

                            JSONArray photosArray = response.getJSONArray("photos");
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
