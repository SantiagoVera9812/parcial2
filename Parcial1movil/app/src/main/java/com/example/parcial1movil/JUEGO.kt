package com.example.parcial1movil

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class JUEGO : AppCompatActivity() {
    companion object {
        val listaDatosPersonas = mutableListOf<String>()
    }
    var indice = 0
    var vidas = 3
    var puntaje = 0
    var latitud:Double =0.0
    var longitud:Double = 0.0
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var Nombre:String
    val palabrasOriginales = listOf(
        "CAMINO",
        "FRUTA",
        "TELEFONO",
        "UNIVERSIDAD",
        "PLANETARIO",
        "MARIPOSA",
        "GUITARRA",
        "ELEFANTE",
        "BIBLIOTECA",
        "RELAMPAGO",
        "CASCADA",
        "ENTENDER",
        "DINAMICO",
        "ENTUSIASMO",
        "CARAMELO",
        "AUTOMOVIL",
        "ELECCION",
        "PARAGUAS",
        "SISTEMATICO",
        "TRAPEZOIDE"
    )

    val palabrasInvertidas = listOf(
        "OCAMIN",
        "FRUAT",
        "EFOLPETNO",
        "ADIVERSIUN",
        "OTARPLENAI",
        "MSAPIARO",
        "RIATUGRA",
        "ENFELATE",
        "BBLCITEOAI",
        "MAARELGPO",
        "ASCACDA",
        "TERNEDNE",
        "ONMICADI",
        "NMOSTEUSIA",
        "MLAECARO",
        "AMOOTLUVI",
        "CIONHELE",
        "AASRAPGU",
        "AISTOTCMESI",
        "OEDTZAPIRE"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        Nombre = intent.getStringExtra("nombre").toString()
        Log.i("Nombre", "Nombre : : " + Nombre)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val permissionsToRequest = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val permissionsNotGranted = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (permissionsNotGranted.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsNotGranted.toTypedArray(),
                PermissionRequest.MULTIPLE_PERMISSIONS
            )
        }
        IniciarJuego()

        val botonAdivinar = findViewById<Button>(R.id.button)
        botonAdivinar.setOnClickListener(){
            VerificarIgualdad()
        }
    }

    fun IniciarJuego(){

        var palabraInvertidaActual = palabrasInvertidas[indice]
        var textto = findViewById<TextView>(R.id.textView)
        textto.text = palabraInvertidaActual
        val textViewVidas = findViewById<TextView>(R.id.textView3)
        textViewVidas.text = "Vidas restantes: $vidas"
        val textViewPuntaje = findViewById<TextView>(R.id.textView4)
        textViewPuntaje.text = "Puntaje: $puntaje"


    }

    fun VerificarIgualdad(){
        val editText = findViewById<EditText>(R.id.editTextText)
        val respuestaUsuario = editText.text.toString()

        if (respuestaUsuario.equals(palabrasOriginales[indice], ignoreCase = true)) {

            puntaje++
            indice++
            val textViewPuntaje = findViewById<TextView>(R.id.textView4)
            textViewPuntaje.text = "Puntaje: $puntaje"
            val editText = findViewById<EditText>(R.id.editTextText)
            editText.text.clear()


        } else {

            vidas--
            val textViewVidas = findViewById<TextView>(R.id.textView3)
            textViewVidas.text = "Vidas restantes: $vidas"
            val mensaje = "La palabra es incorrecta"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        }
        if( vidas == 0){
            val botonAdivinar = findViewById<Button>(R.id.button)
            botonAdivinar.isEnabled = false
            val editText = findViewById<EditText>(R.id.editTextText)
            editText.isEnabled= false


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                mFusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                    Log.i("LOCATION", "onSuccess location")
                    if (location != null) {
                        Log.i("LOCATION", "Longitud: " + location.longitude)
                        longitud = location.longitude
                        Log.i("LOCATION", "Latitud: " + location.latitude)
                        latitud = location.latitude
                        var mensajeLista:String = "("+ latitud.toString()+ ","+ longitud.toString() + ")" + Nombre + ":"+ puntaje
                        listaDatosPersonas.add(mensajeLista)
                    }

                }

            } else {
                val botonAdivinar = findViewById<Button>(R.id.button)
                botonAdivinar.isEnabled = false
                val editText = findViewById<EditText>(R.id.editTextText)
                editText.isEnabled= false
                var mensajeLista:String = "("+ latitud.toString()+ ","+ longitud.toString() + ")" + Nombre + ":"+ puntaje
                listaDatosPersonas.add(mensajeLista)
            }


        }


        if (indice < palabrasOriginales.size) {
            IniciarJuego()
        } else {
            // El juego ha terminado, puedes mostrar un mensaje de fin de juego o realizar acciones adicionales.
        }



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionRequest.MULTIPLE_PERMISSIONS) {
            // Check if all requested permissions were granted
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Both permissions were granted, handle accordingly
            } else {

                Toast.makeText(
                    this,
                    "Access is required for certain features.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}