package com.example.imoondshop.ui.buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imoondshop.databinding.FragmentBuyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuyFragment : Fragment() {

    private val vm by viewModel<BuyViewModel>()
    private var _binding: FragmentBuyBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)


    }

}