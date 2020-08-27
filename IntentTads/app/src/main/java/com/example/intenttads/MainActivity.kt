package com.example.intenttads

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

        btCalcula.setOnClickListener { v: View? ->
            var precoAlcool = txtPrecoAlcool.text.toString()
            var precoGasolina = txtPrecoGasolina.text.toString()

            if(precoAlcool.isNotEmpty() && precoGasolina.isNotEmpty()) {
                var razao = precoAlcool.toDouble() / precoGasolina.toDouble()
                var intent = Intent(this,RespostaActivity::class.java)

                if(razao > 0.7) {
                    //Toast.makeText(this, "O combustível que mais compensa é a Gasolina", Toast.LENGTH_SHORT).show()
                    intent.putExtra("resposta", "Gasolina")
                } else if(razao < 0.7) {
                    //Toast.makeText(this, "O combustível que mais compensa é o Alcool", Toast.LENGTH_SHORT).show()
                    intent.putExtra("resposta", "Alcool")
                } else {
                    //Toast.makeText(this, "Tanto faz!", Toast.LENGTH_SHORT).show()
                    intent.putExtra("resposta", "Tanto faz")
                }

                startActivity(intent);

            } else {
                Toast.makeText(this, "Preencha o preço dos combustíveis", Toast.LENGTH_SHORT).show()
            }

        }

    }
}