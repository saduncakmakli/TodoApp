package com.example.todoapp.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.CardTasarimBinding
import com.example.todoapp.entity.Work
import com.example.todoapp.fragment.AnasayfaFragmentDirections
import com.example.todoapp.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog


class WorkAdapter(var mContext:Context, var workListesi:List<Work>, var viewModel:AnasayfaFragmentViewModel, var fragmentManager:FragmentManager):RecyclerView.Adapter<WorkAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimBinding
        init {
            this.tasarim = tasarim
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val work = workListesi.get(position)
        val t = holder.tasarim

        t.workObject = work
        t.satirCard.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.workGecis(work = work)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.imageViewSilResim.setOnClickListener{
            Snackbar.make(it, "${work.yapilacak_is} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") {
                    viewModel.krepo.removeWork(work.yapilacak_id)
                }.show()
        }
        t.constraintLayoutSatirCard.setBackgroundColor(Color.parseColor(work.yapilacak_color))
        t.imageViewSelectColor.setOnClickListener{

            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                .setInitialColor(Color.parseColor(work.yapilacak_color))
                .setColorModel(ColorModel.HSV)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected{ color: Int ->
                    val hexColor = java.lang.String.format("#%06X", 0xFFFFFF and color)
                    Log.e("ColorLog", hexColor)
                    t.constraintLayoutSatirCard.setBackgroundColor(Color.parseColor(hexColor))
                    viewModel.krepo.updateWork(work.yapilacak_id,work.yapilacak_is,hexColor,work.yapilacak_detay,work.yapilacak_completed)
                }.create()

            colorPicker.show(fragmentManager, "color_picker")
        }

        t.constraintLayoutCheckBox.setOnClickListener {
            var degisim: Int
            if (work.yapilacak_completed == 0)
            {
                degisim = 1
                t.imageViewCheckboxCheck.visibility = View.VISIBLE
            }else
            {
                degisim = 0
                t.imageViewCheckboxCheck.visibility = View.INVISIBLE
            }
            viewModel.krepo.updateWork(work.yapilacak_id,work.yapilacak_is,work.yapilacak_color,work.yapilacak_detay,degisim)
        }
    }

    override fun getItemCount(): Int {
        return workListesi.size
    }

}