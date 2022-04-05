package com.example.exam5.ui.detailsfragment

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.exam5.R
import com.example.exam5.data.Repository
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.network.Service
import com.example.exam5.databinding.FragmentDetailsBinding
import com.example.exam5.ui.homefragment.HomeViewModel
import com.example.exam5.ui.homefragment.HomeViewModelFactory
import java.io.ByteArrayOutputStream

class DetailsFragment:Fragment(R.layout.fragment_details) {
    var imageByteArray: ByteArray? = null
    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var myviewModel:DetailViewModel
    private val cameraluncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        imageByteArray = it.toByteArray()
        binding.imageview.setImageBitmap(it)
    }
    private val selectPictureLuncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val change = context?.contentResolver?.openInputStream(it)?.readBytes()
        imageByteArray = change
        binding.imageview.setImageURI(it)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDetailsBinding.bind(view)
        viewmodelprovider()
        myviewModel.showinfo(args.userid)
        myviewModel.userList.observe(viewLifecycleOwner){
            binding.firstname.text=it.firstName
            binding.lastname.text=it.lastName
            binding.nationalcode.text=it.nationalCode
        }

        changeprofile()
        upload()
    }
    private fun upload() {
        binding.upload.setOnClickListener {
            myviewModel.uploadimage(args.userid, imageByteArray!!)

        }
    }

    private fun changeprofile() {
        binding.imageview.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Selectimage")
            builder.setMessage("Choose your Option:")
            builder.setPositiveButton("Gallery") { dialog, which ->
                dialog.dismiss()
                selectPictureLuncher.launch("image/*")
            }
            builder.setNegativeButton("Cammera") { dialog, which ->
                dialog.dismiss()
                cameraluncher.launch(null)
            }
            builder.create().show()
        }
    }

    private fun viewmodelprovider() {
        val application = requireNotNull(this.activity).application

        val remoteDataSource = RemoteDataSource(Service)

        val repository = Repository(remoteDataSource)

        val factory = DetailViewModelFactory(repository, application)

        myviewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }
    fun Bitmap.toByteArray(): ByteArray {
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG, 10, this)
            return toByteArray()
        }
    }
}