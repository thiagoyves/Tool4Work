package com.ubicomp.tool4work;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayMessageActivity extends AppCompatActivity {

    private Event event;
    private Place current_place;
    private EditText editText_arrival_time;
    private EditText editText_final_time;
    private Switch switch_button;
    private Date arrival_date;
    private Date leave_date;
    private DateFormat formatter;
    private WifiManager wifi;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String event_name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);

//        retrieve the data from the arrival and leave time fields from the view
        editText_arrival_time = (EditText) findViewById(R.id.arrival_time);
        editText_final_time = (EditText) findViewById(R.id.leave_time);
        switch_button = (Switch) findViewById(R.id.switch_wifi);

        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(true);
                }
                else {
                    wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(false);
                }
            }
        });

        formatter = new SimpleDateFormat("hh:mm:ss a");

        editText_arrival_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String arrival_time =  editText_arrival_time.getText().toString();
                    try {
                        arrival_date = (Date)formatter.parse(arrival_time);
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        editText_final_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String leave_time =  editText_final_time.getText().toString();
                    try {
                        arrival_date = (Date)formatter.parse(leave_time);
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        if(arrival_date != null &&  leave_date != null){
            Event new_event = new Event(current_place, arrival_date.getTime(), leave_date.getTime(), event_name);
        }

        PlaceAutocompleteFragment fragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        fragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: obter informações sobre o local selecionado.

                current_place = place;

                Log.i("TAG", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Solucionar o erro.
                Log.i("TAG", "Ocorreu um erro: " + status);
            }
        });

    }

}
