package com.example.imoondshop.ui.product_info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.imoondshop.databinding.FragmentProductInfoBinding
import com.example.imoondshop.ui.adapter.ImagePagerAdapter
import com.example.imoondshop.untils.Constants

class ProductInfoFragment : Fragment() {


    private lateinit var viewModel: ProductInfoViewModel
    private var _binding: FragmentProductInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ProductInfoViewModelFactory(lifecycleScope)).get(
            ProductInfoViewModel::class.java
        )
        val position = arguments?.getInt(Constants.PRODUCT_ID, -1)
        Log.d("TAG", "position product: $position")
        if (position != null) {
            viewModel.getProductById(position)
        }
        viewModel.productData.observe(viewLifecycleOwner) {
            binding.apply {
                val adapter = ImagePagerAdapter(it.images)
                imagePager.adapter = adapter
                tvPriceProductInfo.text = it.price
                tvCategoryProductInfo.text = it.categories[0].name
                tvDescriptionProductInfo.text = it.description
                tvWeightProductInfo.text = it.weight
                tvLengthProductInfo.text = it.dimensions.length
                tvHeightProductInfo.text = it.dimensions.height
                tvWidthProductInfo.text = it.dimensions.width
                tvCostProductInfo.text = it.regular_price
            }

        }

        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.apply {
            listImage.observe(viewLifecycleOwner) {

            }
        }
    }

}