package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.repo.WorksRepository

class WorkFragmentViewModel(application : Application) : AndroidViewModel(application){
    val krepo = WorksRepository(application)

    fun update(yapilacak_id:Int, yapilacak_is:String, yapilacak_color:String, yapilacak_detay:String, yapilacak_completed:Int) {
        krepo.updateWork(yapilacak_id,yapilacak_is,yapilacak_color, yapilacak_detay, yapilacak_completed)
    }
}