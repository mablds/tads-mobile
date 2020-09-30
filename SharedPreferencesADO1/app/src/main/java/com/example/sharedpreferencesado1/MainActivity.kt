package com.example.sharedpreferencesado1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun calculaProvisoes(precoCusto: String, precoVenda: String) : String{
            var provisao = precoVenda.toDouble() - precoCusto.toDouble()
            var resultado = if (provisao > 0) "Lucro: $provisao" else "Prejuízo: $provisao"
            return resultado
        }

        val sh = getSharedPreferences("blocodenotas", Context.MODE_PRIVATE)

        //limpa a caixa de bloco de notas e de anotação
        btLimpar.setOnClickListener { v: View? ->
            txtNomeProduto.text.clear()
            numberPrecoCusto.text.clear()
            numberPrecoVenda.text.clear()
            txtPrevisao.setText("")
        }

        //grava o conteúdo do bloco de notas usando o nome da anotação com chave
        btSalvar.setOnClickListener { v: View? ->
            if (txtNomeProduto.text.isNotEmpty()) {

                var precoCusto = numberPrecoCusto.text.toString().trim()
                var precoVenda = numberPrecoVenda.text.toString().trim()

                sh.edit().putString(txtNomeProduto.text.toString(),"$precoCusto;$precoVenda").apply()
                Toast.makeText(this,"Produto Salvo",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Nome de produto inválido",Toast.LENGTH_SHORT).show()
            }
        }

        btAbrir.setOnClickListener { v: View? ->
            if (txtNomeProduto.text.isNotEmpty()) {
                var texto = sh.getString(txtNomeProduto.text.toString(),"")

                var precoCusto = texto?.split(";")?.get(0)
                var precoVenda = texto?.split(";")?.get(1)

                if (texto.isNullOrEmpty()) {
                    Toast.makeText(this,"Produto vazio ou Inexistente",Toast.LENGTH_SHORT).show()
                } else {
                    numberPrecoCusto.setText(precoCusto)
                    numberPrecoVenda.setText(precoVenda)

                    txtPrevisao.setText(calculaProvisoes(precoCusto.toString(), precoVenda.toString()))

                    Toast.makeText(this,"Anotação Carregada com Sucesso",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Nome da anotação inexistente",Toast.LENGTH_SHORT).show()
            }

        }



    }
}