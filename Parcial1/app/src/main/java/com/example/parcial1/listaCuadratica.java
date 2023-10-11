package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.parcial1.databinding.ActivityListaCuadraticaBinding;
import com.example.parcial1.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class listaCuadratica extends AppCompatActivity {

    private ActivityListaCuadraticaBinding binding;

    String[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaCuadraticaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle reciveData = getIntent().getExtras();
        String number = reciveData.getString("numberRange");
        listaCuadratica(number);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),Actividad3.class);
                Bundle sendData  = new Bundle();
                sendData.putString("numero",binding.listView.getSelectedItem().toString());
                intent.putExtras(sendData);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void listaCuadratica(String number){
        double n = Integer.parseInt(number);
        for(int i = 0 ; i<n;i++){
            n = Math.pow(n,3);
            array[i]=String.valueOf(n);
        }
    }
}