package com.example.imoondshop.ui.category

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
import com.example.imoondshop.databinding.FragmentCategoryBinding
import com.example.imoondshop.ui.adapter.CategoryFragmentAdapter
import com.example.imoondshop.ui.adapter.ProductClickListener
import com.example.imoondshop.untils.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {
    private val vm by viewModel<CategoryViewModel>()
    private var isDownload = false

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                vm.loadData()
            }
        }
        if (!isDownload) {
            lifecycleScope.launch {
                vm.loadData()
            }
            isDownload = true
        }
        vm.apply {
            message.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            isShowProgress.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = it
            }
            categoryLive.observe(viewLifecycleOwner) {
                val adapter = CategoryFragmentAdapter(it,object :ProductClickListener{
                    override fun onClick(position: Int)  {
                        val bundle = bundleOf(Constants.CATEGORY_KEY to it[position].name)

                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(
                            R.id.action_categoryFragment_to_productListFragment,
                            bundle
                        )
                    }

                })
                binding.listCategories.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.listCategories.adapter = adapter
            }
        }
    }

}