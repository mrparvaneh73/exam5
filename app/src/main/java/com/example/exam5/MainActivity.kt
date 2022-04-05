package com.example.exam5

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.exam5.data.Repository
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.network.Service
import com.example.exam5.databinding.ActivityMainBinding
import com.example.exam5.model.User
import com.example.exam5.ui.Myviewmodel
import com.example.exam5.ui.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var football: CheckBox
    private lateinit var singing: CheckBox
    private lateinit var lastname: EditText
    private lateinit var firtname: EditText
    private lateinit var nationalcode: EditText
    private lateinit var save: TextView
    private lateinit var cancel: TextView
    var hobbies:MutableList<String> = mutableListOf()
    private lateinit var myviewModel: Myviewmodel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodelprovider()
        binding.fab.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custome_dialog)
        var user: User? = null
        save = dialog.findViewById(R.id.tv_save)
        cancel = dialog.findViewById(R.id.tv_cancel)
        firtname = dialog.findViewById(R.id.firstname_et)
        football = dialog.findViewById(R.id.football_checkbox)
        singing = dialog.findViewById(R.id.singing_checkbox)
        nationalcode=dialog.findViewById(R.id.nationalcode_et)
        lastname = dialog.findViewById(R.id.lastname_et)
        save.setOnClickListener {
            if (football.isChecked){
                hobbies.add(football.text.toString())
            }
            if(singing.isChecked){
                hobbies.add(singing.text.toString())
        }
            user=User(firstName = firtname.text.toString()
            , lastName = lastname.text.toString(),
                nationalCode = nationalcode.text.toString()
            , hobbies = hobbies
            )
          myviewModel.addUser(user!!)
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun viewmodelprovider() {
        val application = requireNotNull(this).application

        val remoteDataSource = RemoteDataSource(Service)

        val repository = Repository(remoteDataSource)

        val factory = MyViewModelFactory(repository, application)

        myviewModel = ViewModelProvider(this, factory).get(Myviewmodel::class.java)
    }
}