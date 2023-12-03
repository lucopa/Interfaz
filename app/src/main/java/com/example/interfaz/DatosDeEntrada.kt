package com.example.interfaz

import com.example.interfaz.databinding.ActivityDatosDeEntradaBinding



import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.interfaz.databinding.ActivityMainBinding


class DatosDeEntrada : AppCompatActivity() {
    private lateinit var binding : ActivityDatosDeEntradaBinding
    private var films : MutableList<String>
    private lateinit var adapter : ArrayAdapter<String>

    init {
        films = Films.namesFilms.toMutableList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosDeEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
        initAdapter()
    }


    private fun initAdapter() {
        adapter = ArrayAdapter (
            this,
            R.layout.simple_dropdown_item_1line,
            films
        )
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.spinner.adapter = adapter
    }




    private fun initEvent() {
        proveCheck()
        proveCheck()
        proveRadio()
        proveSwitch()
        proveSpinner()
        proveToggle()
        proveButtonActionEditor()
    }

    private fun proveButtonActionEditor() {
        binding.editText.setOnEditorActionListener{
                v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND){
                showMsg("Clase, capturo el evento del Action del teclado")
                true
            }else
                false
        }


    }


    private fun proveRadio(){
        binding.radioButton1.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Radio Button 1 seleccionada")
            }else{
                showMsg("Radio Button 1 deseleccionado")
            }
        }

        binding.radioButton2.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Radio Button 2 seleccionada")
            }else{
                showMsg("Radio Button 2 deseleccionada")
            }
        }

        binding.radioButton3.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Radio Button 3 seleccionada")
            }else{
                showMsg("Radio Button 3 eseleccionado")
            }
        }
    }



    private fun proveCheck(){
        binding.checkBox1.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Casilla 1 seleccionada")
            }else{
                showMsg("Casilla 1 deseleccionado")
            }
        }

        binding.checkBox2.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Casilla 2 seleccionada")
            }else{
                showMsg("Casilla 2 deseleccionada")
            }
        }

        binding.checkBox3.setOnCheckedChangeListener{
                btnView, isCheked ->
            if (isCheked){
                showMsg("Casilla 3 seleccionada")
            }else{
                showMsg("Casilla 3 eseleccionado")
            }
        }
    }





    private fun proveSwitch(){
        binding.switchButton.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked){
                showMsg("Boton Switch activado")
            }else{
                showMsg("Boton Switch Desactivado")
            }
        }
    }





    private fun proveSpinner(){
        val films = Films.namesFilms[0].split(", ") // Obtiene el array de nombres de películas
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, films)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFilm = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Seleccionaste: $selectedFilm", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  cuando no se selecciona nada
            }
        }
    }






    private fun proveToggle(){
        binding.toggleButton.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked){
                showMsg("Boton Toggle activado")
            }else{
                showMsg("Boton Toggle Desactivado")
            }
        }
    }



    private fun showMsg(cad : String){
        Toast.makeText(this, cad, Toast.LENGTH_LONG).show()
    }


}

