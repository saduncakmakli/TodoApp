package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.repo.WorksRepository

class AddWorkFragmentViewModel(application : Application) : AndroidViewModel(application) {

    val krepo = WorksRepository(application)

    fun add(yapilacak_is:String) {
        krepo.addWork(yapilacak_is)
    }
}