package com.example.espacobemestar

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_servico_detalhes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.Float.Companion.toString as toString1

class ServicoDetalheActivity :DebugActivity() {
    var servicodetalhe: Servico? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico_detalhes)

        servicodetalhe = intent.getSerializableExtra("servico_detalhe") as Servico

        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title =servicodetalhe?.nome


        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados da disciplina
        nomeServico.text = servicodetalhe?.nome
        valorServico.text = "R$ " + servicodetalhe?.valor.toString()
        Picasso.with(this).load(servicodetalhe?.foto).fit().into(imagemServico,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}

                override fun onError() { }
            })
    }
}

