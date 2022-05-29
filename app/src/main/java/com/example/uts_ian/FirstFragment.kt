package com.example.uts_ian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val LoginButton=view?.findViewById<Button>(R.id.LoginButton) as Button
        LoginButton.setOnClickListener{
            val Username=view?.findViewById<EditText>(R.id.Username) as EditText
            val Password=view?.findViewById<EditText>(R.id.Password) as EditText
            val logname=Username.text.toString()
            val logpass=Password.text.toString()
            val logname1=""
            val logpass1=""

            if (logname==logname1 && logpass==logpass1){
                Toast.makeText(activity, "username or password is wrong!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }
}