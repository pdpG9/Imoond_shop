package com.example.imoondshop.ui.product_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentProductInfoBinding
import com.example.imoondshop.ui.adapter.ImagePagerAdapter
import com.example.imoondshop.untils.Constants
import com.example.imoondshop.untils.showProgress
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductInfoFragment : Fragment() {


    private val vm by viewModel<ProductInfoViewModel>()
    private var _binding: FragmentProductInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val position = arguments?.getInt(Constants.PRODUCT_ID, -1)
        Log.d("TAG", "position product: $position")
        if (position != null) {
            lifecycleScope.launch {
                vm.getProductById(position)
            }
        }
        vm.loadData()
        vm.productData.observe(viewLifecycleOwner) {
            binding.apply {
                Log.d("TAG", "onViewStateRestored: ${it.images.size}")
                val adapter = ImagePagerAdapter(it.images)
                imagePager.adapter = adapter
                tvPriceProductInfo.text = it.price
                tvCategoryProductInfo.text = it.category
                tvDescriptionProductInfo.text = it.description
                tvCostProductInfo.text = it.regular_price
            }

        }

        binding.apply {

            btBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btShoppingCard.setOnClickListener {
                findNavController().navigate(R.id.basketFragment)
//                findNavController().navigate(R.id.action_productInfoFragment_to_basketFragment)
            }
            btAddToCard.setOnClickListener {
                lifecycleScope.launch {
                vm.addToCard()
                }
            }
            btBuyNow.setOnClickListener {
                val bundle = bundleOf(Constants.PRODUCT_KEY to vm.productData.value)
                findNavController().navigate(R.id.action_productInfoFragment_to_buyFragment, bundle)
            }

        }
        vm.apply {
            message.observe(viewLifecycleOwner) {
                binding.itemProgress.tvNoResult.text = it
               // Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            isShowProgress.observe(viewLifecycleOwner) {
               binding.itemProgress.progressBar.showProgress(it)
            }
            countBasketPr.observe(viewLifecycleOwner){
            binding.apply {
                if (it>0){
                cvCountProductBasket.visibility = View.VISIBLE
                }
                tvCountProductBasket.text = it.toString()
            }
            }
        }
    }

}