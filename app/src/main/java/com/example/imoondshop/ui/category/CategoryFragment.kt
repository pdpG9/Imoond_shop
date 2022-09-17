package com.example.imoondshop.ui.category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imoondshop.databinding.FragmentCategoryBinding
import com.example.imoondshop.ui.adapter.CategoryFragmentAdapter

class CategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel
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
        viewModel = ViewModelProvider(
            this,
            CategoryFragmentFactory(lifecycleScope = this.lifecycleScope)
        ).get(CategoryViewModel::class.java)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }
        if (!isDownload) {
            viewModel.loadData()
            isDownload = true
        }
        viewModel.apply {
            isRefresh.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = it
            }
            categoryLive.observe(viewLifecycleOwner) {
                val adapter = CategoryFragmentAdapter(it)
                binding.listCategories.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.listCategories.adapter = adapter
            }
        }
    }

}