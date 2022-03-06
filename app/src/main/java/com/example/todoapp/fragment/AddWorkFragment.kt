package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddWorkBinding
import com.example.todoapp.viewmodel.AddWorkFragmentViewModel
import com.example.todoapp.viewmodel.AddWorkVMF

class AddWorkFragment : Fragment() {
    private lateinit var tasarim:FragmentAddWorkBinding
    private lateinit var viewModel:AddWorkFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_add_work, container, false)
        tasarim.addWorkFragment = this
        tasarim.addWorkToolbarBaslik = "Add Work"


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AddWorkFragmentViewModel by viewModels(){
            AddWorkVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonKaydetTikla(yapilacak_is:String){
        viewModel.add(yapilacak_is)
    }
}

