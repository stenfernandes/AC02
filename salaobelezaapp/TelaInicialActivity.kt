package com.msn.cfaaraujo.salaobelezaapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    private val Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intent
        // intent é um atributo herdado de activity
        val args = intent.extras

        // recuperar parametros do tipo string

        val nome = args?.getString("nome")

        // recuperar parametros simplicado
        val numero = intent.getIntExtra("nome", 0)



        Toast.makeText(this, "logado com sucesso $nome", Toast.LENGTH_LONG).show()


        mensagemInicial.text = "Bem Vindo $nome"

        //botaoSair.setOnClickListener{cliqueSair()}

        //alterar titulo da ActionBar
        supportActionBar?.title = "Espaço Bem Estar"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // colocar toolbar
       // setSupportActionBar(toolbar)

    }

    // Clique do botao sair
    fun cliqueSair(){
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saida do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em Buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Clicou em Configurar", Toast.LENGTH_LONG).show()
        }


        return super.onOptionsItemSelected(item)
    }
}

