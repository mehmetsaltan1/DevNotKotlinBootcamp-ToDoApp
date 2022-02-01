package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.repo.YapilacaklarDaoRepository

class AnasayfaViewModel(application: Application) : AndroidViewModel(application) {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()

    val yrepo = YapilacaklarDaoRepository(application)

    init {
        yapilacaklariYukle()
        yapilacaklarListesi = yrepo.yapilacaklariGetir()
    }

    fun ara(aramaKelimesi: String) {
        yrepo.yapilacakAra(aramaKelimesi)
    }

    fun sil(yapilacak_id: Int) {
        yrepo.yapilacakSil(yapilacak_id)
    }

    fun yapilacaklariYukle() {
        yrepo.tumYapilacaklariAl()
    }
}