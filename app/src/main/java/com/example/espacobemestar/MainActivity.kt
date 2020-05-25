package com.example.espacobemestar

import android.R.attr.password
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.login.*


class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        campo_imagem.setImageResource(R.drawable.login)

        texto_login.text = getString(R.string.mensagem_login)

        // segunda forma: delegar para método
        botao_login.setOnClickListener {onClickLogin() }

        //  retirar
        campo_usuario.setText("")
        campo_senha.setText("")

        var lembrar = Prefs.getBoolean("lembrar")
        var usuario = Prefs.getString("lembrarNome")
        var senha = Prefs.getString("lembrarSenha")
        campo_usuario.setText(usuario)
        campo_senha.setText(senha)
        checkLembrar.isChecked = lembrar

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onClickLogin(){
        val loginUsuario = campo_usuario.text.toString()
        val loginSenha = campo_senha.text.toString()

        Prefs.setBoolean("lembrar", checkLembrar.isChecked)
        if (checkLembrar.isChecked){
            Prefs.setString("lembrarNome", loginUsuario)
            Prefs.setString("lembrarSenha", loginSenha)
        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")

        }

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        intent.putExtra("nome", loginUsuario)

        // fazer a chamada
        //startActivity(intent)

        Thread {

            var usuarioService =  UsuarioService
            var resultado = usuarioService.Login(context,user = loginUsuario,password = loginSenha)

            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                // Executa a telaInicial
                //if (loginUsuario=="aluno" && loginSenha=="impacta") {
                if(resultado==true){
                    startActivityForResult(intent, 1)
                }
                else{
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("Alerta")
                    alerta.setMessage("Usuário ou senha incorretos")
                    alerta.show()
                }
                campo_usuario.setText("")
                campo_senha.setText("")
            }
        }.start()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }










}

