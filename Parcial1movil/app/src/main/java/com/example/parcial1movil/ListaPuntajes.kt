package com.example.parcial1movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class ListaPuntajes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_puntajes)
        // Obtener el ListView desde el layout
        val listView = findViewById<ListView>(R.id.listViewPuntajes)

        // Obtener la lista de datos desde tu JUEGO
        val lista = JUEGO.listaDatosPersonas
        val listaOrdenada = lista.sortedByDescending { elemento ->
            val puntajeStr = elemento.substringAfter(':').trim()
            puntajeStr.toIntOrNull() ?: 0
        }
        Log.d("ListaDatos", JUEGO.listaDatosPersonas.toString())
        // Crear un ArrayAdapter y asignarlo al ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaOrdenada)
        listView.adapter = adapter

    }
}