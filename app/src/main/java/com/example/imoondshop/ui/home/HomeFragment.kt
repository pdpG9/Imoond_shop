package com.example.imoondshop.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentHomeBinding
import com.example.imoondshop.ui.adapter.CategoryAdapter
import com.example.imoondshop.ui.adapter.ProductAdapter
import com.example.imoondshop.ui.adapter.ProductClickListener
import com.example.imoondshop.untils.Constants


class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var isDownload = false
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(this.lifecycleScope)
        )[HomeViewModel::class.java]
        if (!isDownload){
        viewModel.loadData()
            isDownload = true
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }

        viewModel.apply {
            productLive.observe(viewLifecycleOwner) {
                val adapter = ProductAdapter(it,object :ProductClickListener{
                    override fun onClick(position: Int) {
                        val bundle = bundleOf(Constants.PRODUCT_ID to position)
                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(R.id.action_homeFragment_to_productInfoFragment,bundle)
                    }

                })

                binding.apply {
                    listProduct.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    listProduct.adapter = adapter
                }
            }
            isRefresh.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = it
            }
            categoryLive.observe(viewLifecycleOwner) {
                val adapter = CategoryAdapter(it)
                binding.listCategory.adapter = adapter
            }
            isClickBtTopProd.observe(viewLifecycleOwner) {
                if (it) {
                    binding.btTopProducts.setBackgroundResource(R.drawable.bg_selected_button_home)
                    binding.btRecommendedProducts.setBackgroundResource(R.color.white)
                } else {
                    binding.btTopProducts.setBackgroundResource(R.color.white)
                    binding.btRecommendedProducts.setBackgroundResource(R.drawable.bg_selected_button_home)
                }
            }

        }

        binding.apply {
            btNotificationLayout.btNotification.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
            }
            btTopProducts.setOnClickListener {
                viewModel.clickTopProductBt()
            }
            btRecommendedProducts.setOnClickListener {
                viewModel.clickRecommendedProdBt()
            }
            binding.searchLayout.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }


    }

}