package com.example.uts_ian

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.uts_ian.R

class ThirdFragment : Fragment(){
    val args : ThirdFragmentArgs by navArgs()
    private var uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view=inflater.inflate(R.layout.fragment_third,container,false)
        val valuee1=args.nim
        val valuee2=args.nama
        val valuee3=args.panggilan
        val valuee4=args.alamat
        val showNIM=view?.findViewById<TextView>(R.id.showNIM) as TextView
        val showNamaLengkap=view?.findViewById<TextView>(R.id.showNamaLengkap) as TextView
        val showNamaPanggilan=view?.findViewById<TextView>(R.id.showNamaPanggilan) as TextView
        val showAlamat=view?.findViewById<TextView>(R.id.showAlamat) as TextView
        val showPhoto=view?.findViewById<ImageView>(R.id.showFoto) as ImageView
        showNIM.text=valuee1
        showNamaLengkap.text=valuee2
        showNamaPanggilan.text=valuee3
        showAlamat.text=valuee4
        val sharedPreferences:SharedPreferences=view.context.getSharedPreferences("pref",Context.MODE_PRIVATE)
        val photo=sharedPreferences.getString("imageUser","")
        uri= Uri.parse(photo)
        showPhoto.setImageURI(uri)
        return view
    }
}