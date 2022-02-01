package com.example.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.CardTasarimBinding
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.fragment.AnasayfaFragmentDirections
import com.example.todoapp.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class YapilacaklarAdapter(
    var mContext: Context,
    var yapilacaklarListesi: List<Yapilacaklar>,
    var viewModel: AnasayfaViewModel
) :
    RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: CardTasarimBinding) :
        RecyclerView.ViewHolder(cardTasarimBinding.root) {
        var cardTasarimBinding: CardTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val yapilacak = yapilacaklarListesi.get(position)
        val t = holder.cardTasarimBinding
        t.yapilacakNesnesi = yapilacak

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.anasayfaToDetaySayfa(yapilacak)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${yapilacak.yapilacak_is} silinsin mi ?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.sil(yapilacak.yapilacak_id)

                }.show()
        }
    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }

}