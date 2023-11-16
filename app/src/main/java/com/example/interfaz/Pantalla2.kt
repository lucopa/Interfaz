package com.example.interfaz


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class Pantalla2 : AppCompatActivity() {
    private val REQUEST_CALL_PERMISSION = 1

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)

        val botonLlamada: ImageButton = findViewById(R.id.boton_llamada)
        botonLlamada.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                hacerLlamada()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL_PERMISSION
                )
            }
        }
    }


    private fun hacerLlamada() {
        val numeroTelefono = "tel:674695275"
        val intentLlamada = Intent(Intent.ACTION_CALL, Uri.parse(numeroTelefono))
        startActivity(intentLlamada)
    }





}
