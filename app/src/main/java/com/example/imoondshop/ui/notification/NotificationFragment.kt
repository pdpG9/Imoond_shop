package com.example.imoondshop.ui.notification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var viewModel: NotificationViewModel
    private var _binding:FragmentNotificationBinding? = null
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater,container,false)
    return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
       binding!!.btBack.setOnClickListener {
           findNavController().popBackStack()
       }
    }

}