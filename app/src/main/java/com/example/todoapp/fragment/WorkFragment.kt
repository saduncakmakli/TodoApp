package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentWorkBinding
import com.example.todoapp.viewmodel.WorkFragmentViewModel
import com.example.todoapp.viewmodel.WorkMF

class WorkFragment : Fragment() {
    private lateinit var tasarim : FragmentWorkBinding
    private lateinit var viewModel : WorkFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_work, container, false)

        tasarim.workFragment = this
        tasarim.workToolbarBaslik = "Work Detail"

        val bundle : WorkFragmentArgs by navArgs()
        val gelenKisi = bundle.work

        tasarim.workObject = gelenKisi


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : WorkFragmentViewModel by viewModels(){
            WorkMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonGuncelleTikla(yapilacak_id:Int, yapilacak_is:String, yapilacak_color:String, yapilacak_detay:String, yapilacak_completed:Int){
        viewModel.update(yapilacak_id,yapilacak_is,yapilacak_color, yapilacak_detay, yapilacak_completed)
    }
}