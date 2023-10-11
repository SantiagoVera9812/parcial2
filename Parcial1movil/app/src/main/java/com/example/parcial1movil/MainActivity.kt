package com.example.parcial1movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonJuego = findViewById<Button>(R.id.button2)
        val botonuntaje = findViewById<Button>(R.id.button3)
        val editTextNombre = findViewById<EditText>(R.id.editTextText3)
        botonJuego.setOnClickListener(){
            val nombre = editTextNombre.text.toString() // Obtener el texto del EditText
            val intentJuego = Intent(this, JUEGO::class.java)
            intentJuego.putExtra("nombre", nombre) // Pasar el nombre a través del Intent
            editTextNombre.text.clear()
            startActivity(intentJuego)
        }
        botonuntaje.setOnClickListener(){
            val intentPuntajes = Intent(this, ListaPuntajes::class.java)
            startActivity(intentPuntajes)
        }
    }
}