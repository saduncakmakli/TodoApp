package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Work
import com.example.todoapp.repo.WorksRepository

class AnasayfaFragmentViewModel (application : Application) : AndroidViewModel(application) {
    var workList = MutableLiveData<List<Work>>()
    val krepo = WorksRepository(application)

    init {
        getAllWorks()
        workList = krepo.getWorks()
    }

    fun ara(aramaKelimesi:String){
        krepo.searchWork(aramaKelimesi)
    }

    fun remove(yapilacak_id:Int){
        krepo.removeWork(yapilacak_id)
    }

    fun getAllWorks(){
        krepo.gelAllWork()
    }
}