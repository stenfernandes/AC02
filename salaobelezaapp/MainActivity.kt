package com.msn.cfaaraujo.salaobelezaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        imageView2.setImageResource(R.drawable.imagem_login)
        mensagem_login.setText(R.string.str_login)

        botao_login.setOnClickListener{
            var intent = Intent(this, TelaInicialActivity::class.java)

            val params = Bundle()
            params.putString("nome","fernando sousa")
            params.putInt("n", 10)
            params.putIntegerArrayList("lista_n", arrayListOf(1,2,3,4,5))

            intent.putExtras(params)
            startActivity(intent)


        }

    }
}

