package com.example.prova1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        var resultado = intent.getStringExtra("Resultado")?.split(";")
        var altura = resultado?.get(0);
        var peso = resultado?.get(1);
        var imc = resultado?.get(2);

        resultadoText.setText("Altura: $altura\n\nPeso: $peso\n\nIMC: $imc")

        btHistorico.setOnClickListener { v: View? ->
            var intent = Intent(this, HistoricoActivity::class.java)

            startActivity(intent)
        }

        btHome.setOnClickListener { v: View? ->
            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}