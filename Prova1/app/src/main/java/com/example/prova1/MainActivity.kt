package com.example.prova1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("historicoIMC", Context.MODE_PRIVATE)

        btCalcular.setOnClickListener { v: View? ->
            if(!validaCampos()) {
                Toast.makeText(this,"Ambos os campos devem ser preenchidos.",Toast.LENGTH_SHORT).show()
            } else {
                var altura = alturaNumber.text.toString().toDouble();
                var peso = pesoNumber.text.toString().toDouble();
                var imc = calculaImc(altura, peso);

                var count:Int;

                if(sh.contains("count")) {
                    count = sh.getInt("count", 0)
                    count++;
                } else {
                    count = 1;
                }

                sh.edit().putInt("count", count).apply()
                sh.edit().putString(count.toString(), "$altura;$peso;$imc").apply()

                Toast.makeText(this,"Cálculo IMC realizado e salvo no histórico",Toast.LENGTH_SHORT).show()

                var intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra("Resultado", "$altura;$peso;$imc")

                startActivity(intent)
            }

        }

        btHistorico.setOnClickListener { v: View? ->
            var intent = Intent(this, HistoricoActivity::class.java)

            startActivity(intent)
        }

        btLimpar.setOnClickListener { view: View? ->
            alturaNumber.setText("")
            pesoNumber.setText("")
        }

    }

    fun calculaImc(altura: Double, peso: Double) :String {
        var result = peso / (altura * altura);
        return "%.2f".format(result)
    }

    fun validaCampos() :Boolean {
        return !alturaNumber.text.toString().trim().equals("") && !pesoNumber.text.toString().trim().equals("")
    }
}