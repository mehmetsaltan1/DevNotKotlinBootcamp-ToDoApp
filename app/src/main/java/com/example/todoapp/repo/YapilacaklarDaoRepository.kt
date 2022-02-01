package com.example.todoapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Yapilacaklar
import com.example.todoapp.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YapilacaklarDaoRepository(var application: Application) {
    var yapilacaklarListesi: MutableLiveData<List<Yapilacaklar>>
    var vt: Veritabani

    init {
        yapilacaklarListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application.applicationContext)!!
    }

    fun yapilacaklariGetir(): MutableLiveData<List<Yapilacaklar>> {
        return yapilacaklarListesi

    }

    fun yapilacakKayit(yapilacak_ad: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniYapilacak = Yapilacaklar(0, yapilacak_ad)
            vt.yapilacaklarDao().yapilacakEkle(yeniYapilacak)
        }

    }

    fun yapilacakGuncelle(yapilacak_id: Int, yapilacak_ad: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenIs = Yapilacaklar(yapilacak_id, yapilacak_ad)
            vt.yapilacaklarDao().yapilacakGuncelle(guncellenenIs)
        }
    }

    fun yapilacakAra(aramaKelimesi: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = vt.yapilacaklarDao().yapilacakAra(aramaKelimesi)
        }
    }

    fun yapilacakSil(yapilacak_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinecekIs = Yapilacaklar(yapilacak_id, "")
            vt.yapilacaklarDao().yapilacakSil(silinecekIs)
            tumYapilacaklariAl()
        }
    }

    fun tumYapilacaklariAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = vt.yapilacaklarDao().tumYapilacaklar()
        }
    }
}