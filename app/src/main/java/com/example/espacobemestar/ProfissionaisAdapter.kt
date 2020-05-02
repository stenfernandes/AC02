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

class ProfissionaisAdapter (
    val profissionais:List<Profissionais>, val onClick: (Profissionais) -> Unit
):RecyclerView.Adapter<ProfissionaisAdapter.ProfissionaisViewHolder>() {

        // ViewHolder com os elemetos da tela
        class ProfissionaisViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val cardNomeP: TextView
            val cardImagemP : ImageView
            var cardProgress: ProgressBar
            var cardView: CardView

            init {
                cardNomeP = view.findViewById<TextView>(R.id.cardNomeP)
                cardImagemP = view.findViewById<ImageView>(R.id.cardImagemP)
                cardProgress = view.findViewById<ProgressBar>(R.id.cardProgressP)
                cardView = view.findViewById<CardView>(R.id.card_profissionais)

            }

        }

        // Quantidade de profissionais na lista

        override fun getItemCount() = this.profissionais.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfissionaisViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_profissionais, parent, false)

            // retornar ViewHolder
            val holder = ProfissionaisViewHolder(view)
            return holder
        }


        override fun onBindViewHolder(holder: ProfissionaisViewHolder, position: Int) {

            val context = holder.itemView.context

            val profissionais = profissionais[position]
            holder.cardNomeP.text = profissionais.nome
            holder.cardProgress.visibility = View.VISIBLE

            Picasso.with(context).load(profissionais.foto).fit().into(
                holder.cardImagemP,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

            holder.itemView.setOnClickListener {onClick(profissionais)}
        }
}
