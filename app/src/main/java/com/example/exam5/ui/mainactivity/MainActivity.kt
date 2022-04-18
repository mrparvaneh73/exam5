package com.example.exam5.ui.mainactivity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.exam5.R
import com.example.exam5.databinding.ActivityMainBinding
import com.example.exam5.databinding.CustomeDialogBinding
import com.example.exam5.model.CreateUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dialogBinding: CustomeDialogBinding
    private val navController by lazy { findNavController(R.id.myNavHostFragment) }
    var hobbies: MutableList<String> = mutableListOf()
    private lateinit var dialog: Dialog

    private val myviewModel by viewModels<Myviewmodel>()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.fab.setOnClickListener {
            showDialog()
        }
    }
    fun init(){
        dialog = Dialog(this@MainActivity)
        dialogBinding = CustomeDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    private fun showDialog() = dialogBinding.apply {


        var user: CreateUser? = null

        tvSave.setOnClickListener {
            if (footballCheckbox.isChecked) {
                hobbies.add(footballCheckbox.text.toString())
            }
            if (singingCheckbox.isChecked) {
                hobbies.add(singingCheckbox.text.toString())
            }
            user = CreateUser(
                firstName = firstnameEt.text.toString(), lastName = lastnameEt.text.toString(),
                nationalCode = nationalcodeEt.text.toString()
//            , hobbies = hobbies
            )
            myviewModel.creatUser(user!!)
            dialog.dismiss()
        }
        tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}