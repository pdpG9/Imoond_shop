package com.example.imoondshop.ui.notification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imoondshop.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var vm: NotificationViewModel
    private var _binding:FragmentNotificationBinding? = null
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater,container,false)
    return binding!!.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        vm = ViewModelProvider(this).get(NotificationViewModel::class.java)
       binding!!.btBack.setOnClickListener {
           findNavController().popBackStack()
       }
        vm.notifications.observe(viewLifecycleOwner){
            binding!!.tvNotNotif.visibility = View.INVISIBLE
        }

    }

}