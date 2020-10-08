package com.example.prova1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_historico.*
import kotlinx.android.synthetic.main.activity_resultado.*
import kotlinx.android.synthetic.main.activity_resultado.btHome

class HistoricoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val sh = getSharedPreferences("historicoIMC", Context.MODE_PRIVATE);

        var count = sh.getInt("count", 0)

        var historico: String = ""

        if(count != 0) {
            for(i in 1..count) {
                var hist = sh.getString(i.toString(), "")?.split(";")
                var altura = hist?.get(0)
                var peso = hist?.get(1)
                var imc = hist?.get(2)

                historico += "Altura: $altura\nPeso: $peso\nIMC: $imc\n\n"
            }
        }

        historicoText.setText(if(count == 0) "Histórico vazio" else historico)

        btHome.setOnClickListener { v: View? ->
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btApagar.setOnClickListener { view: View? ->
            sh.edit().putInt("count", 0).apply()
            historicoText.setText("Histórico vazio")
        }
    }
}