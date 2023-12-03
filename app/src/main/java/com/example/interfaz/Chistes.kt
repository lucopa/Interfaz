package com.example.interfaz

import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.interfaz.databinding.ActivityChistesBinding
import java.util.*

class Chistes : AppCompatActivity() {

    private lateinit var binding: ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var handler: Handler
    val MYTAG = "LOGCAT"


    private val chistes = listOf(
        "¿Por qué los pájaros no usan Facebook? Porque ya tienen Twitter.",
        "¿Cuál es el animal más antiguo? La cebra, porque está en blanco y negro.",
        "¿Cuál es el colmo de un jardinero? Que su novia se llame Rosa y le deje plantado.",
        "¿Qué le dice un jardinero a otro? ¡Qué cultivado eres!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTextToSpeech()
        initHandler()
        initEvent()
    }

    private fun initHandler() {
        handler = Handler(Looper.getMainLooper())
        binding.progressBar.visibility = View.VISIBLE
        binding.btnExample.visibility = View.GONE

        Thread {
            Thread.sleep(3000)
            handler.post {
                binding.progressBar.visibility = View.GONE
                val description = getString(R.string.describe).toString()
                speakMeDescription(description)
                Thread.sleep(4000)
                Log.i(MYTAG, "Se ejecuta correctamente el hilo")
                binding.btnExample.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                val locale = Locale("es", "ES")
                val result = textToSpeech.setLanguage(locale)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e(MYTAG, "El idioma español no está disponible")
                } else {
                    Log.i(MYTAG, "Configuración de TextToSpeech en español")
                }
            } else {
                Log.e(MYTAG, "Error en la configuración de TextToSpeech")
            }
        })
    }


    private fun initEvent() {
        binding.btnExample.setOnClickListener {
            val random = Random()
            val randomChiste = chistes[random.nextInt(chistes.size)]
            speakMeDescription(randomChiste)
        }
    }

    private fun speakMeDescription(s: String) {
        Log.i(MYTAG, "Intentando hablar")
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
