package com.example.todoapp.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.adapter.WorkAdapter
import com.example.todoapp.databinding.FragmentAnasayfaBinding
import com.example.todoapp.viewmodel.AnasayfaFragmentViewModel
import com.example.todoapp.viewmodel.AnasayfaVMF

class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this

        tasarim.anasayfaToolbarBaslik = "To Do List"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        //tasarim.rv.layoutManager = LinearLayoutManager(requireContext()) //Alt alta
        //tasarim.rv.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL) //Scroll edilebilir

        viewModel.workList.observe(viewLifecycleOwner) {
                val adapter = WorkAdapter(requireContext(),it,viewModel,parentFragmentManager )
                tasarim.workAdapter = adapter
        }

        return tasarim.root

    }

    fun fabTikla(v:View){
        Navigation.findNavController(v).navigate(R.id.addWorkGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : AnasayfaFragmentViewModel by viewModels(){
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllWorks()
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_ara -> {
                Log.e("Kişi Ara Icon", "Seçildi")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }*/
}