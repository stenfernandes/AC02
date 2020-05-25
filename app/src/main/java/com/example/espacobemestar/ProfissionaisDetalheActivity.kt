package com.example.espacobemestar

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profissionais_detalhes.*
import kotlinx.android.synthetic.main.activity_servico_detalhes.*
import kotlinx.android.synthetic.main.toolbar.*

class ProfissionaisDetalheActivity :DebugActivity() {
        var profissionaisdetalhe: Profissionais? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_profissionais_detalhes)

            profissionaisdetalhe = intent.getSerializableExtra("profissionais_detalhe") as Profissionais

            setSupportActionBar(toolbar)

            // alterar título da ActionBar
            supportActionBar?.title =profissionaisdetalhe?.nome


            // up navigation
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            nomePDetalhes.text = profissionaisdetalhe?.nome
            especializacaoDetalhes.text = profissionaisdetalhe?.especializacao
            Picasso.with(this).load(profissionaisdetalhe?.foto).fit().into(imagemPDetalhes,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
        }
}

