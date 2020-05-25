package com.example.espacobemestar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AgendamentoAdapter (
    val agendamentos:List<Agendamento>, val onClick: (Agendamento) -> Unit
): RecyclerView.Adapter<AgendamentoAdapter.AgendamentoViewHolder>() {

    class AgendamentoViewHolder (view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImag: ImageView


        init {
            cardNome= view.findViewById(R.id.cardNome)
            cardImag= view.findViewById(R.id.cardImag)


        }

    }

    override fun getItemCount() =this.agendamentos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_servico, parent, false)

        val holder = AgendamentoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: AgendamentoViewHolder, position: Int) {

        val context = holder.itemView.context

        val agendamento = agendamentos[position]

        holder.cardNome.text = agendamento.servico + " " + agendamento.data
        //holder.cardProcess.visibility = View.VISIBLE



        holder.itemView.setOnClickListener { onClick(agendamento) }
    }

}