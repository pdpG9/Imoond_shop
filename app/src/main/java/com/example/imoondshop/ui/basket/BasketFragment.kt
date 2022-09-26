package com.example.imoondshop.ui.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentBasketBinding
import com.example.imoondshop.ui.adapter.BasketProductAdapter
import com.example.imoondshop.ui.adapter.ProductClickListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : Fragment() {

    private val vm by viewModel<BasketViewModel>()
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

        vm.apply {
            loadData()
            productIdsCard.observe(viewLifecycleOwner){
                lifecycleScope.launch {
                loadProducts(it)
                }
            }
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
            products.observe(viewLifecycleOwner){
                binding.rvProductBasket.adapter = BasketProductAdapter(it,object :ProductClickListener{
                    override fun onClick(amountAllPrice: Int) {
                        binding.tvAllValueProductInCard.text = amountAllPrice.toString()
                    }

                })
                lifecycleScope.launch {
                    loadRecommendedProducts()
                }
            }
        }

        binding.rbtAll.setOnClickListener {
            vm.clickAllBtCheck()
        }
    }

}