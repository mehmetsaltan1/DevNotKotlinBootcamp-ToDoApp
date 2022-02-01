package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.repo.YapilacaklarDaoRepository

class KayitFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val yrepo = YapilacaklarDaoRepository(application)
    fun kayit(yapilacak_Ad: String) {
        yrepo.yapilacakKayit(yapilacak_Ad)
    }
}