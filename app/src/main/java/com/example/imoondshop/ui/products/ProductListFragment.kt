package com.example.imoondshop.ui.products

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
import com.example.imoondshop.databinding.FragmentProductListBinding
import com.example.imoondshop.ui.adapter.ProductAdapter
import com.example.imoondshop.ui.adapter.ProductClickListener
import com.example.imoondshop.untils.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val vm by viewModel<ProductListViewModel>()
    private var _binding:FragmentProductListBinding? = null
    private val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryName = arguments?.getString(Constants.CATEGORY_KEY," ")
        Log.d("TAG", "onActivityCreated: ${categoryName}")

        lifecycleScope.launch {
        vm.loadData(categoryName!!)
        }
        binding.apply {
            btBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvCategoryName.text = categoryName
            rvProducts.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        vm.apply {
            productLive.observe(viewLifecycleOwner){
                binding.rvProducts.adapter = ProductAdapter(it,object :ProductClickListener{
                    override fun onClick(position: Int)
                    {
                        val bundle = bundleOf(Constants.PRODUCT_ID to position)
                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_productInfoFragment,
                            bundle
                        )
                    }
                })
            }
        }
    }

}