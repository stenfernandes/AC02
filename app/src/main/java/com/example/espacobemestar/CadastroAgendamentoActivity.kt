package com.example.espacobemestar

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_cadastro_agendamento.*
import kotlinx.android.synthetic.main.activity_servico.*
import kotlinx.android.synthetic.main.activity_servico.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_servico.menu_lateral
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.concurrent.schedule

class CadastroAgendamentoActivity : DebugActivity (), NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    private val context: Context get() = this
    var spinnerServico:Spinner? = null
    var spinnerHorario:Spinner? = null
    private var servicoSelecionado:String = ""
    private var dataHorarioSelecionado:String = ""


    private var servicos = listOf<Servico>()

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_agendamento)

        agendar.setOnClickListener {onClickAgendar() }
        //val args = intent.extras
        // val titulo = args?.getString("tituloTela")


        Thread {
            var horarios = AgendamentoService.getAgendamento(context,"Massagem")

            this.servicos = ServicoService.getServico(context)
            runOnUiThread {

                val list: MutableList<String> = ArrayList()
                for (i in this.servicos ) {
                    list.add(i.nome)
                }

                spinnerServico = this.selecionar_servico
                spinnerServico?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selecionado = parent?.getItemAtPosition(position) as String
                        Toast.makeText(context, "Opção escolhida: $selecionado", Toast.LENGTH_SHORT).show()
                        servicoSelecionado = selecionado
                    }
                }
                val aa = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerServico!!.setAdapter(aa)

                // Horarios


                val listHorarios: MutableList<String> = ArrayList()
                for (i in horarios ) {
                    listHorarios.add(i.data)
                }

                spinnerHorario = this.Selecionar_data_horario
                spinnerHorario?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selecionado = parent?.getItemAtPosition(position) as String
                        dataHorarioSelecionado = selecionado
                        Toast.makeText(context, "Opção escolhida: $selecionado", Toast.LENGTH_SHORT).show()
                    }
                }
                val aahorario = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listHorarios)
                aahorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerHorario!!.setAdapter(aahorario)
            }
        }.start()




        // colocar toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Cadastro de Agendamento"
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        //Configuração do recyclerview
        recyclerServico?.layoutManager = LinearLayoutManager(context)
        recyclerServico?.itemAnimator = DefaultItemAnimator()
        recyclerServico?.setHasFixedSize(true)

        //ATUALIZAR PROGRESSBAR2 INVISIVEL
       // progressBar2.visibility = View.INVISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onClickAgendar() {
        Thread {
            spinnerServico = this.selecionar_servico
            spinnerHorario = this.Selecionar_data_horario

            var usuarioService =  UsuarioService
            var aggnew = Agendamento()
            aggnew.servico = servicoSelecionado
            aggnew.data = dataHorarioSelecionado
            aggnew.username = "aluno"

            AgendamentoService.save(agendamento = aggnew)

            runOnUiThread {
                Toast.makeText(context, "Agendamento Realizado", Toast.LENGTH_LONG).show()

            }
        }.start()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()
        taskServico()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun taskServico() {

        Thread {

            // this.servicos = ServicoService.getServico(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                //  recyclerServico?.adapter = ServicoAdapter(this.servicos){ onClickServicoDetalhe(it) }
                //  enviaNotificacao(servicos.get(1))
            }
        }.start()


    }


    fun enviaNotificacao(servico: Servico) {
        val intent = Intent(this, ServicoActivity::class.java)
        intent.putExtra("servico", servico)
        NotificationUtil.create(
            this,
            1,
            intent,
            "Título - EMSApp",
            "Há um novo Serviço ${servico.nome}"
        )
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

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
        if (id == R.id.action_buscar) {

        } else if (id == R.id.action_atualizar) {
            progressBar2.visibility = View.VISIBLE
            Timer("SettingUp", false).schedule(10000) {
                progressBar2.visibility = View.INVISIBLE
            }
            //} else if (id == R.id.action_config) {
            //    Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_sair) {
            sairApp()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    //menu lateral
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

    //variavel para sair confirmar saída da tela
    private fun sairApp() {
        val alerta = AlertDialog.Builder(this)
        alerta.setTitle("Sair")
        alerta.setMessage("Deseja realmente sair?")
        alerta.setPositiveButton(
            "Sim", DialogInterface.OnClickListener(
                function = { dialog: DialogInterface, which: Int ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("result", "Saída do App")
                    startActivityForResult(intent, 1)
                    finish()
                })
        )
        alerta.setNegativeButton(
            "Não", DialogInterface.OnClickListener(
                function = { dialog: DialogInterface, which: Int -> })
        )
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
}


