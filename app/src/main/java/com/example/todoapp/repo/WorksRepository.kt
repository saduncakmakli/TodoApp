package com.example.todoapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.entity.Work
import com.example.todoapp.room.Veritabani
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorksRepository(var application : Application) {
    var workListesi:MutableLiveData<List<Work>>
    var vt:Veritabani

    init{
        workListesi = MutableLiveData()
        vt = Veritabani.veritabaniErisim(application)!!
    }

    fun getWorks() : MutableLiveData<List<Work>>{
        return workListesi
    }

    fun addWork(yapilacak_is:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newWork = Work(0,yapilacak_is,"#FFFFFF", "", 0)
            vt.worksDao().addWorkDatabase(newWork)
        }
    }

    fun updateWork(yapilacak_id:Int, yapilacak_is: String, yapilacak_color: String, yapilacak_detay:String, yapilacak_completed:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updatingWork = Work(yapilacak_id,yapilacak_is, yapilacak_color, yapilacak_detay, yapilacak_completed)
            vt.worksDao().updateWorkDatabase(updatingWork)
        }
    }

    fun searchWork(aramaKelimesi:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            workListesi.value = vt.worksDao().searchWork(aramaKelimesi)
        }
    }

    fun removeWork(yapilacak_id:Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deletedWork = Work(yapilacak_id, "", "", "", 0)
            vt.worksDao().removeWorkDatabase(deletedWork)
            gelAllWork()
        }
    }

    fun gelAllWork(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            workListesi.value = vt.worksDao().allWorks()
        }
    }
}