package com.example.exam5.ui.detailsfragment

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.exam5.R
import com.example.exam5.data.remote.Resource
import com.example.exam5.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    var imageByteArray: ByteArray? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    private val myviewModel by viewModels<DetailViewModel>()

    private val cameraluncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            imageByteArray = it!!.toByteArray()
            binding.imageview.setImageBitmap(it)
        }
    private val selectPictureLuncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            val change = context?.contentResolver?.openInputStream(it!!)?.readBytes()
            imageByteArray = change
            binding.imageview.setImageURI(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        myviewModel.showinfo(args.userid)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myviewModel.usersInfo.collect{
                    binding.firstname.text = it.firstName
                    binding.lastname.text = it.lastName
                    binding.nationalcode.text = it.nationalCode


                }
            }
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


    fun Bitmap.toByteArray(): ByteArray {
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG, 10, this)
            return toByteArray()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}