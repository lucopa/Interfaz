package com.example.interfaz



import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

import java.util.Calendar


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton : ImageButton = findViewById(R.id.llamada)
        boton.setOnClickListener{
            val intent: Intent = Intent (this, Pantalla2:: class.java)
            startActivity(intent)
        }

        val boton2 : ImageButton = findViewById(R.id.imageDados)
        boton2.setOnClickListener{
            val intent: Intent = Intent (this, Dados:: class.java)
            startActivity(intent)
        }

        val boton3 : ImageView = findViewById(R.id.imageLobo)
        boton3.setOnClickListener{
            val intent: Intent = Intent (this, DatosDeEntrada:: class.java)
            startActivity(intent)
        }

        val boton4 : ImageButton = findViewById(R.id.botonChiste)
        boton4.setOnClickListener{
            val intent: Intent = Intent (this, Chistes:: class.java)
            startActivity(intent)
        }




        val alarma: ImageButton = findViewById(R.id.imageAlarma)
        alarma.setOnClickListener() {
            abrirAlarma()

        }

        val botonURL: ImageButton = findViewById(R.id.url)
        botonURL.setOnClickListener {
            abrirURL("https://www.wikipedia.com")
        }

        val btnOpenYouTube: ImageButton = findViewById(R.id.otro)
        btnOpenYouTube.setOnClickListener {
            abrirYoutube()
        }
    }


    private fun abrirYoutube() {
        val youtubeUri = Uri.parse("https://www.youtube.com")
        val youtubeIntent = Intent(Intent.ACTION_VIEW, youtubeUri)

        youtubeIntent.setPackage("com.google.android.youtube")

        if (youtubeIntent.resolveActivity(packageManager) != null) {
            startActivity(youtubeIntent)
        } else {

            val webIntent = Intent(Intent.ACTION_VIEW, youtubeUri)
            startActivity(webIntent)
        }
    }
    private fun abrirURL(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun abrirAlarma() {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma")
            putExtra(AlarmClock.EXTRA_HOUR, Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
            putExtra(AlarmClock.EXTRA_MINUTES, Calendar.getInstance().get(Calendar.MINUTE) + 2)
        }
        startActivity(intent)
    }

}







