package com.example.imoondshop.ui.basket

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {

    private lateinit var viewModel: BasketViewModel
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]

        viewModel.apply {
            btAllCheck.observe(viewLifecycleOwner){
                if (it){
                    binding.apply {
                    rbtAll.setImageResource(R.drawable.ic_check)
                        btCheckout.isClickable = it
                        btCheckout.setBackgroundResource(R.drawable.bg_button_red)
                    //    cardCheckOut.setCardBackgroundColor(R.color.red_for_bt)
                    }

                }else{
                    binding.apply {
                        rbtAll.setImageResource(R.drawable.ic_uncheck)
                        btCheckout.isClickable = it
                        btCheckout.setBackgroundResource(R.drawable.bg_button_uncheck)
                      //  cardCheckOut.setCardBackgroundColor(R.color.grey)
                    }
                }
            }
        }

        binding.rbtAll.setOnClickListener {
            viewModel.clickAllBtCheck()
        }
    }

}