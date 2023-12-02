package com.example.interfaz

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Dados : AppCompatActivity() {
    var suma = 0
    var vaso: ImageView? = null
    var dado1: ImageView? = null
    var dado2: ImageView? = null
    var dado3: ImageView? = null
    var textResultadoFinal: TextView? = null
    var clickFinal: Long = 0
    var adivinar: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        vaso = findViewById(R.id.imgVaso)
        dado1 = findViewById(R.id.imgDado1)
        dado2 = findViewById(R.id.imgDado2)
        dado3 = findViewById(R.id.imgDado3)
        textResultadoFinal = findViewById(R.id.txtFinal)
        adivinar = findViewById(R.id.texto)

        vaso?.setOnClickListener {
            if (SystemClock.elapsedRealtime() - clickFinal < 3500) {
                return@setOnClickListener
            }
            clickFinal = SystemClock.elapsedRealtime()
            tirarDados()
        }
    }

    fun tirarDados() {
        val milisegundos = 500
        var i = 1
        val numeroAdivinar = adivinar?.text.toString().toIntOrNull()

        if (numeroAdivinar == null || numeroAdivinar <= 3 || numeroAdivinar >= 18) {
            Toast.makeText(
                this,
                "Introduce un numero",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        textResultadoFinal?.visibility = View.INVISIBLE

        while (i < 6) {
            Tirar(milisegundos * i)
            i++
        }
        MostrarResultados(milisegundos * i)
    }

    fun Tirar(milisegundos: Int) {
        val handler = Handler()
        handler.postDelayed({ mostrarDados() }, milisegundos.toLong())
    }

    fun MostrarResultados(milisegundos: Int) {
        val handler = Handler()
        handler.postDelayed({ mostrarResultado() }, milisegundos.toLong())
    }

    fun mostrarDados() {
        val valor1 = (1 + Math.random() * 6).toInt()
        val valor2 = (1 + Math.random() * 6).toInt()
        val valor3 = (1 + Math.random() * 6).toInt()

        val dados = listOf(dado1, dado2, dado3)

        for ((index, dado) in dados.withIndex()) {
            val valor = when (index) {
                0 -> valor1
                1 -> valor2
                else -> valor3
            }
            mostrarDado(dado, valor)
        }

        suma = valor1 + valor2 + valor3
    }

    private fun mostrarDado(dado: ImageView?, valor: Int) {
        dado?.setImageResource(getDrawableIdForDado(valor))
    }

    private fun getDrawableIdForDado(valor: Int): Int {
        return when (valor) {
            1 -> R.drawable.dado1
            2 -> R.drawable.dado2
            3 -> R.drawable.dado3
            4 -> R.drawable.dado4
            5 -> R.drawable.dado5
            else -> R.drawable.dado6
        }
    }

    private fun mostrarResultado() {
        textResultadoFinal?.text = suma.toString()
        textResultadoFinal?.visibility = View.VISIBLE
        val numeroParaAdivinar = adivinar?.text.toString().toInt()

        if (numeroParaAdivinar == suma) {
            Toast.makeText(this, "¡He adivinado el numero!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Has ganado pero la proxima vez te ganaré", Toast.LENGTH_SHORT).show()
        }
    }
}
