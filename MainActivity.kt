package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val indiceActual = savedInstanceState?.getInt(KEY_INDEX,0)?:0
        quizViewModel.indiceActual = indiceActual



        binding.btnFalso.setOnClickListener { incorrectToast() }
        binding.btnVerdadero.setOnClickListener { correctToast() }
        binding.btnSiguiente.setOnClickListener { siguientePregunta() }
        binding.Respuesta.setOnClickListener { siguientePagina() }

        actualizarPregunta()

    }



    private fun incorrectToast(){
        comprobarRespuesta(false)
    }
    private fun correctToast(){
        comprobarRespuesta(true)
    }
    private fun siguientePregunta(){
        quizViewModel.siguiente()
        actualizarPregunta()

    }
    private fun actualizarPregunta(){
        val textoPregunta = quizViewModel.preguntaActual
        binding.tvPregunta.text = textoPregunta
    }
    private fun comprobarRespuesta(respuestaUsuario: Boolean){
        val respuestaCorrecta = quizViewModel.respuestaActual
        val mensajeRespuesta = if (respuestaUsuario == respuestaCorrecta){
            R.string.correcto
        }else {
            R.string.incorrecto
        }

        Toast.makeText(this,mensajeRespuesta,Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putInt(KEY_INDEX,quizViewModel.indiceActual)
    }
    private fun siguientePagina(){
        val respuestaCorrecta = quizViewModel.respuestaActual

        //Quiero pasarle a la actividad trampas el valor de la respuesta correcta mediante el companion object que ha creado en la actividad trampas

        val intent = Trampas.newIntent(this@MainActivity, respuestaCorrecta)
        startActivity(intent)
    }



}