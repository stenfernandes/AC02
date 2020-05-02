package com.example.espacobemestar

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.core.text.set
import androidx.fragment.app.DialogFragment
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

    }

    fun onClickLogin(){
        val loginUsuario = campo_usuario.text.toString()
        val loginSenha = campo_senha.text.toString()

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        intent.putExtra("nome", loginUsuario)

        // fazer a chamada
        //startActivity(intent)

        // Executa a telaInicial
        if (loginUsuario=="aluno" && loginSenha=="impacta") {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}