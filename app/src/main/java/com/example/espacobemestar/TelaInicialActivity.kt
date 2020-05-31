package com.example.espacobemestar


import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.concurrent.schedule


class TelaInicialActivity : DebugActivity() , NavigationView.OnNavigationItemSelectedListener {


    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intent
        // intent é um atributo herdado de Activity
        val args = intent.extras
        val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        Toast.makeText(context, "Bem-Vindo", Toast.LENGTH_LONG).show()

        //ATUALIZAR PROGRESSBAR2 INVISIVEL
        progressBar2.visibility = View.INVISIBLE

        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = "Espaço Bem-Estar"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        //clique do botão serviço
        botao_servico.setOnClickListener {onClickServico() }
        //clique do botão profissionais
        botao_profissionais.setOnClickListener {onClickProfissionais("Profissionais") }
        //clique do botão Agendamento
        botao_agendamento.setOnClickListener {onClickAgendamento() }


    }


    fun OnClickServico(servico: Servico){
        Toast.makeText(context, "Clicou em ${servico.nome}", Toast.LENGTH_LONG).show()
    }

    fun OnClickProfissionais(profissionais: Profissionais){
        Toast.makeText(context, "Clicou em ${profissionais.nome}", Toast.LENGTH_LONG).show()
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(context, newText, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                return false
            }

        })
        return true
    }

    //OPÇÕES DO ITEM NA PRIMEIRA TELA DE ENTRADA
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {

        }
        else if (id == R.id.action_atualizar) {
            progressBar2.visibility = View.VISIBLE
            Timer("SettingUp", false).schedule(10000) {
                progressBar2.visibility=View.INVISIBLE
            }
        //} else if (id == R.id.action_config) {
        //    Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if(id == R.id.action_sair) {
               sairApp()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral () {
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.nav_open,
            R.string.nav_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener (this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_servico -> {
                onClickServico()
            }
            R.id.nav_profissionais -> {
                onClickProfissionais()
            }
            R.id.nav_cadastro_agendamento -> {
                Toast.makeText(this, "Realize seu Agendamento", Toast.LENGTH_SHORT).show()
                onClickCadastroAgendamento()

            }
            R.id.nav_agendamentos -> {
                onClickAgendamento()
            }


            R.id.nav_contato -> {
                onClickContato()
            }


            //R.id.nav_ajuda -> {
            //    Toast.makeText(this, "Estamos aqui para ajuda-lo", Toast.LENGTH_SHORT).show()
           // }
            R.id.nav_localizacao -> {
               startActivity(Intent(this, MapaActivity::class.java))
                onClickMapa()
            }


            // }
            // R.id.nav_config -> {
            //     Toast.makeText(this,"Clicou em configurações", Toast.LENGTH_SHORT).show()
            // }
            R.id.nav_sair-> {
                sairApp()
            }

        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    //função para sair app
    private fun sairApp() {
        val alerta = AlertDialog.Builder(this)
        alerta.setTitle("Sair")
        alerta.setMessage("Deseja realmente sair?")
        alerta.setPositiveButton("Sim", DialogInterface.OnClickListener(
            function = { dialog: DialogInterface, which: Int ->
                val returnIntent = Intent()
                returnIntent.putExtra("result","Saída do App")
                setResult(Activity.RESULT_OK,returnIntent)
                finish()
            }))
        alerta.setNegativeButton("Não", DialogInterface.OnClickListener(
            function = { dialog: DialogInterface, which: Int -> }))
        alerta.show()
    }


    fun onClickServico() {
        val intent = Intent(context, ServicoActivity::class.java)
        intent.putExtra("tituloTela", "Serviço")
        //StartActivity que abre a tela
        startActivityForResult(intent, 1)
    }

    fun onClickCadastroAgendamento() {
        val intent = Intent(context, CadastroAgendamentoActivity::class.java)
        intent.putExtra("tituloTela", "Agendamento")
        //StartActivity que abre a tela
        startActivityForResult(intent, 1)
    }

    fun onClickProfissionais(nomeTela: String) {
        val intent = Intent(context, ProfissionaisActivity::class.java)
        intent.putExtra("tituloTela", nomeTela)
        startActivityForResult(intent, 1)
    }

    fun onClickAgendamento() {
        val intent = Intent(context, AgendamentoActivity::class.java)
        intent.putExtra("nome", "")
        startActivityForResult(intent, 1)
    }

    fun onClickProfissionais() {
        val intent = Intent(context, ProfissionaisActivity::class.java)
        intent.putExtra("tituloTela", "Profissionais")
        //StartActivity que abre a tela
        startActivityForResult(intent, 1)
    }

    fun onClickMapa() {
        val intent = Intent(context, MapaActivity::class.java)
        intent.putExtra("tituloTela", "Localização")
        //StartActivity que abre a tela
        startActivityForResult(intent, 1)
    }



    fun onClickContato() {
        val intent = Intent(context, ContatoActivity::class.java)
        intent.putExtra("tituloTela", "Contato")
        //StartActivity que abre a tela
        startActivityForResult(intent, 1)
    }




}
