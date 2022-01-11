package com.example.quiz

import androidx.lifecycle.ViewModel


class QuizViewModel:ViewModel() {

    var indiceActual = 0
    private val listaPreguntas = listOf(
        Preguntas("El oceano pacifico es mas grande que el oceano atlantico?",true),
        Preguntas("Madrid es la capital de España?",true),
        Preguntas("El rio Amazonas es el rio mas grnade de america?",true),
        Preguntas("El canal de Suez conecta el mar rojo con el oceano indico?",false),
        Preguntas("El origen del rio nilo se encuentra en áfrica?",false)
    )

    val respuestaActual:Boolean
        get() = listaPreguntas[indiceActual].respuesta

    val preguntaActual:String
        get() = listaPreguntas[indiceActual].pregunta

    fun siguiente(){
        indiceActual = (indiceActual + 1 ) % listaPreguntas.size
    }
}