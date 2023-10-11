package com.example.permisos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactosBtn:ImageButton = findViewById(R.id.imageButton)

        val Camara:ImageButton = findViewById(R.id.imageButton2)

        contactosBtn.setOnClickListener(){
            val intent:Intent= Intent(baseContext,MainActivity2::class.java)
            startActivity(intent)
        }

    }
}