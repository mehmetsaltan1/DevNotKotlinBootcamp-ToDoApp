package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.repo.YapilacaklarDaoRepository

class DetayFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var yrepo = YapilacaklarDaoRepository(application)
    fun guncelle(yapilacak_id: Int, yapilacak_ad: String) {
        yrepo.yapilacakGuncelle(yapilacak_id, yapilacak_ad)
    }
}