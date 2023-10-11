package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.parcial1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //logicActivity();

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //funcion(v);
                Intent intent = new Intent(v.getContext(),Actividad3.class);
                startActivity(intent);
            }
        });
    }

    public void logicActivity(){

    }

    public int funcion(View v1){
        String string = binding.editTextTextPersonName2.getText().toString();
        Integer number = Integer.valueOf(string);

        if(number<0 || number>20){
            Toast.makeText(v1.getContext(), "El número ingresado no está en el rango permitido", Toast.LENGTH_SHORT).show();
            return -1;
        }
        Intent intent = new Intent(v1.getContext(),Actividad3.class);
        Bundle sendData  = new Bundle();
//       Send the number to the other activity
        sendData.putString("numberRange",binding.editTextTextPersonName2.getText().toString());
        intent.putExtras(sendData);
        startActivity(intent);

        return  0 ;

    }
}