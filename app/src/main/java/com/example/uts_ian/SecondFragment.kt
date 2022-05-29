package com.example.uts_ian

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.uts_ian.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: SecondFragment?=null
    private var uri: Uri?=null

    private val binding get()=_binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val Submit=view.findViewById<Button>(R.id.SubmitButton) as Button
            var UploadFoto1=view.findViewById<ImageView>(R.id.uploadfoto) as ImageView
            val NIM=view.findViewById<EditText>(R.id.NIM) as EditText
            val NamaLengkap=view.findViewById<EditText>(R.id.NamaLengkap) as EditText
            val NamaPanggilan=view.findViewById<EditText>(R.id.NamaPanggilan) as EditText
            val Alamat=view.findViewById<EditText>(R.id.Alamat) as EditText
            val sharedPreferences: SharedPreferences=view.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        UploadFoto1.setOnClickListener{
            checkPermissionForImage()
            Toast.makeText(context, "photo", Toast.LENGTH_SHORT).show()
        }
        Submit.setOnClickListener{
            val value1=NIM.text.toString()
            val value2=NamaLengkap.text.toString()
            val value3=NamaPanggilan.text.toString()
            val value4=Alamat.text.toString()
            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            editor.putString("imageUser", uri.toString())
            editor.apply()
            val action=SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                value1, value2, value3, value4
            )
            Navigation.findNavController(view).navigate(action)
        }
    }
    private fun openGalleryForImage(){
        val intent =Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, 1)

    }
    private fun checkPermissionForImage(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if((context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED)&&(context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED)){
                val permission=arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse= arrayListOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission,100)
                requestPermissions(permission,100)
            }
            else{
                openGalleryForImage()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK&&requestCode==1){
            val UploadFoto=view?.findViewById<ImageView>(R.id.uploadfoto) as ImageView
            UploadFoto.setImageURI(data?.data)
            uri=data?.data!!
        }
    }
}