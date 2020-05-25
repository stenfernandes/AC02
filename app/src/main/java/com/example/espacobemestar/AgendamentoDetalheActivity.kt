package com.example.espacobemestar

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_servico_detalhes.*
import kotlinx.android.synthetic.main.toolbar.*

class AgendamentoDetalheActivity:DebugActivity() {
    var agendadetalhe: Agendamento? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico_detalhes)

        agendadetalhe = intent.getSerializableExtra("agendamento_detalhe") as Agendamento

        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title =agendadetalhe?.servico


        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados da disciplina
        nomeServico.text = agendadetalhe?.servico
        valorServico.text = agendadetalhe?.data

    }
}

