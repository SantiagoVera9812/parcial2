package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.parcial1.databinding.ActivityActividad3Binding;
import com.example.parcial1.databinding.ActivityListaCuadraticaBinding;

public class Actividad3 extends AppCompatActivity {
    private ActivityActividad3Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActividad3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle reciveData = getIntent().getExtras();
        String number = reciveData.getString("numero");
        binding.textView.setText(number);
    }
}