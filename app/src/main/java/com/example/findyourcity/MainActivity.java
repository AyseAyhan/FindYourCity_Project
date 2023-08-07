package com.example.findyourcity;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
//thread, işlemi arka planda yapar

public class MainActivity extends AppCompatActivity {

    Context context = this;
    EditText textId;
    Button buttonSearch;
    TextView textViewCity;
    private AutoCompleteTextView autoCompleteTextViewSearch;
    ArrayList<CityModel> cityModelList = new ArrayList<>();
    CityListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textId = findViewById(R.id.editTextId);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewCity = findViewById(R.id.textViewCity);
        autoCompleteTextViewSearch = findViewById(R.id.completeTextViewCity);

        new Thread() {
            @Override
            public void run() {
                super.run();
                cityModelList = CityJsonReader.readJsonCity(getApplicationContext());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupSearchAdapter();
                    }
                });
            }
        }.start();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdSearch();
            }
        });

        autoCompleteTextViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityModel selectedCity = (CityModel) parent.getItemAtPosition(position);
                showCityDetails(selectedCity);
            }
        });
    }

    private void performCitySearch() {
        long input = Long.parseLong(textId.getText().toString());
        boolean cityFound = false;
        for (CityModel city : cityModelList) {
            if (input == city.getId()) {
                showCityDetails(city);
                cityFound = true;
                break;
            }
        }
        if (!cityFound) {
            textViewCity.setText("No matching city found for ID: " + input);
        }
    }
    private void setupSearchAdapter() {
        adapter = new CityListAdapter(this, cityModelList);
        autoCompleteTextViewSearch.setAdapter(adapter);
    }

    private void IdSearch() {
        long input = Long.parseLong(textId.getText().toString());
        //loop**
        CityModel city = com.annimon.stream.Stream.of(cityModelList).filter(c -> c.getId() == input).findFirst().orElse(null);
        if (city != null) {
            showCityDetails(city);
        } else {
            textViewCity.setText("No matching city found for ID: " + input);
        }
    }

    private void showCityDetails(CityModel city) {
        textViewCity.setText("\tCity Information\t" +
                "\nId: " + city.getId() +
                "\nName: " + city.getName() +
                "\nState: " + city.getState() +
                "\nCountry: " + city.getCountry() +
                "\nCoordinates:\nCity Latitude: " + city.getCoord().getLat() +
                "\nCity Longitude: " + city.getCoord().getLon());
    }

    //Not: yavaş yüklendiği için input için biraz bekle
}
