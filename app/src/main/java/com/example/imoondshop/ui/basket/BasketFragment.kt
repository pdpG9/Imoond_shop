package com.example.imoondshop.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentBasketBinding
import com.example.imoondshop.ui.adapter.BasketProductAdapter
import com.example.imoondshop.untils.ProductClickListener
import com.example.imoondshop.untils.showProgress
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : Fragment() {

    private val vm by viewModel<BasketViewModel>()
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var noItemText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        progressBar = binding.itemProgress.progressBar
        noItemText = binding.itemProgress.tvNoResult
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        noItemText.text = ""
            vm.loadData()
        vm.apply {
            message.observe(viewLifecycleOwner){
                noItemText.text = it
            }
            isShowProgress.observe(viewLifecycleOwner){
                progressBar.showProgress(it)
            }
            productIdsCard.observe(viewLifecycleOwner){
                lifecycleScope.launch {
                loadProducts(it)
                }
            }
            btAllCheck.observe(viewLifecycleOwner){
                clickOnAllCheck(it)
            }
            products.observe(viewLifecycleOwner){
                progressBar.showProgress(false)
                binding.rvProductBasket.adapter = BasketProductAdapter(it,object :
                    ProductClickListener {
                    override fun onClick(position: Int) {
                        binding.tvAllValueProductInCard.text = position.toString()
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

    private fun clickOnAllCheck(l:Boolean){
        if (l){
            binding.apply {
                rbtAll.setImageResource(R.drawable.ic_check)
                btCheckout.isClickable = l
                btCheckout.setBackgroundResource(R.drawable.bg_button_red)
            }

        }else{
            binding.apply {
                rbtAll.setImageResource(R.drawable.ic_uncheck)
                btCheckout.isClickable = l
                btCheckout.setBackgroundResource(R.drawable.bg_button_uncheck)
            }
        }
    }
}
