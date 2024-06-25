package com.example.game

import android.graphics.Color
import  android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarJuego()
    }

    fun inicializarJuego(){
        val numeros = generaNum(0,9)


        val btn_izq = findViewById<Button>(R.id.btn_izquierda)
        val btn_der = findViewById<Button>(R.id.btn_derecho)

        btn_izq.text = numeros[0].toString()
        btn_der.text = numeros[1].toString()
        btn_izq.setBackgroundColor(Color.GRAY) // Color por defecto
        btn_der.setBackgroundColor(Color.GRAY) // Color por defecto
        btn_izq.setTextColor(Color.WHITE)
        btn_der.setTextColor(Color.WHITE)
        btn_izq.isEnabled = true
        btn_der.isEnabled = true
    }

    private fun generaNum(min: Int, max: Int): List<Int>{
        val numero1 = (min..max).random()
        var numero2 = (min..max).random()
        while (numero2 == numero1) {
            numero2 = (min..max).random()
        }
        return listOf(numero1, numero2)
    }

    fun ValidarIzquierdo(view: View){
        validarBotones(R.id.btn_izquierda, R.id.btn_derecho)
    }

    fun ValidarDerecha(view: View){
        validarBotones(R.id.btn_derecho, R.id.btn_izquierda)
    }
    private fun validarBotones(idBtnPrimario: Int, idBtnSecundario: Int){
        val btnPrimario = findViewById<Button>(idBtnPrimario)
        val btnSecundario = findViewById<Button>(idBtnSecundario)

        val numeroPrimario = btnPrimario.text.toString().toIntOrNull()
        val numeroSecundario = btnSecundario.text.toString().toIntOrNull()

        if (numeroPrimario != null && numeroSecundario != null) {
            if (numeroPrimario > numeroSecundario) {
                btnPrimario.setBackgroundColor(Color.GREEN)
                btnSecundario.isEnabled = false
                btnPrimario.setTextColor(Color.BLACK)
            } else {
                btnPrimario.setBackgroundColor(Color.RED)
                btnSecundario.setBackgroundColor(Color.GREEN)
                btnSecundario.setTextColor(Color.BLACK)
                btnSecundario.isEnabled = false
            }
        }

        // Programar el reinicio después de 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            inicializarJuego()
        }, 5000)
    }
}