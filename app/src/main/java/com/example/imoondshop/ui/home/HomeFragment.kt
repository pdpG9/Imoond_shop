package com.example.imoondshop.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private val vm by viewModel<HomeViewModel>()
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

        if (!isDownload) {
            lifecycleScope.launch {
                vm.loadData()
            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                vm.loadData()
            }
        }

        vm.apply {
            message.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            isShowProgress.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = it
            }

            productLive.observe(viewLifecycleOwner) {
                val adapter = ProductAdapter(it, object : ProductClickListener {
                    override fun onClick(position: Int) {
                        val bundle = bundleOf(Constants.PRODUCT_ID to position)
                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_productInfoFragment,
                            bundle
                        )
                    }

                })

                binding.apply {
                    listProduct.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    listProduct.adapter = adapter
                }
            }
            categoryLive.observe(viewLifecycleOwner) {
                val adapter = CategoryAdapter(it,object :ProductClickListener{
                    override fun onClick(position: Int) {
                        val bundle = bundleOf(Constants.CATEGORY_KEY to it[position].name)

                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_productListFragment,
                            bundle
                        )
                    }

                })
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
            btNotification.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
            }

            btTopProducts.setOnClickListener {
                vm.clickTopProductBt()
            }
            btRecommendedProducts.setOnClickListener {
                vm.clickRecommendedProdBt()
            }
            binding.searchLayout.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }


    }

}