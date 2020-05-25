package com.example.espacobemestar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ServicoAdapter (
    val servico:List<Servico>, val onClick: (Servico) -> Unit
):RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    class ServicoViewHolder (view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImag: ImageView
        val cardProcess: ProgressBar
        val cardView: CardView

        init {
            cardNome= view.findViewById(R.id.cardNome)
            cardImag= view.findViewById(R.id.cardImag)
            cardProcess= view.findViewById(R.id.cardProgress)
            cardView = view.findViewById(R.id.card_servico)
        }

    }

    override fun getItemCount() =this.servico.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_servico, parent, false)

        val holder = ServicoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {

        val context = holder.itemView.context

        val servico = servico[position]

        holder.cardNome.text = servico.nome
        holder.cardProcess.visibility = View.VISIBLE

        Picasso.with(context).load(servico.foto).fit().into(
            holder.cardImag,
            object : com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProcess.visibility =View.GONE
                }

                override fun onError() {
                    holder.cardProcess.visibility =View.GONE
                }
            }
        )

        holder.itemView.setOnClickListener { onClick(servico) }
    }

}