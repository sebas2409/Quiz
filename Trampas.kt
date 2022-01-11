package com.example.quiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.databinding.ActivityTrampasBinding as ActivityTrampasBinding1

private const val EXTRA_ANSWER_IS_TRUE = "answer is true"

class Trampas : AppCompatActivity() {

    private var respuestaCorrectaGuardada = false

    private lateinit var binding: ActivityTrampasBinding1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrampasBinding1.inflate(layoutInflater)
        setContentView(binding.root)

        respuestaCorrectaGuardada = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false) // se extrae el valor de la respuesta que se ha guardad en la constante.

        binding.showAnswerButton.setOnClickListener {
            val textoRespuesta:String = if (respuestaCorrectaGuardada){  // se cambia el texto de la respuesta segun el valor de la respuesta extraida del Extra intent.
                "Verdadero"
            }  else {
                "Falso"
            }
            binding.answerTextView.text = textoRespuesta
        }
    }

    companion object{
        fun newIntent(packageContext: Context,respuestaQueSeraGuardada: Boolean): Intent{
            return Intent(packageContext,Trampas::class.java).apply {

                // guarda el valor de la respuesta, que se guardara al tocar el boton en la main activity en la constante creada en esta actividad.
                putExtra(EXTRA_ANSWER_IS_TRUE,respuestaQueSeraGuardada) }
        }
    }

}