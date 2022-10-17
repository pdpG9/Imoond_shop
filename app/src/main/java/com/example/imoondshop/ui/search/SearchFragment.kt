package com.example.imoondshop.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentSearchBinding
import com.example.imoondshop.ui.adapter.ProductAdapter
import com.example.imoondshop.untils.Constants
import com.example.imoondshop.untils.ProductClickListener
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val vm by viewModel<SearchViewModel>()
    private val binding get() = _binding!!
    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var noItemText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        progressBar = binding.progressIndicator.progressBar
        noItemText = binding.progressIndicator.tvNoResult
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.btBack.setOnClickListener {
            findNavController().popBackStack(R.id.searchFragment, true)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("TAG", "onQueryTextSubmit: $p0")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("TAG", "onQueryTextChange: $p0")
                lifecycleScope.launch {
                    vm.getProductsByName(p0.toString())
                }
                return true
            }

        })
        binding.apply {
            searchView.setOnClickListener(object : View.OnLongClickListener, View.OnClickListener {
                override fun onLongClick(p0: View?): Boolean {
                    return true
                }

                override fun onClick(p0: View?) {
                    searchView.isIconified = false
                }

            })
            rvResultSearch.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemNotification.btNotification.setOnClickListener {
                findNavController().navigate(R.id.action_searchFragment_to_notificationFragment)
            }
            showProgressBar(false)
        }
        vm.apply {
            products.observe(viewLifecycleOwner) {
                val adapter = ProductAdapter(it, object : ProductClickListener {
                    override fun onClick(position: Int) {
                        val bundle = bundleOf(Constants.PRODUCT_ID to position)
                        Log.d("TAG", "onClick: $position")
                        findNavController().navigate(
                            R.id.action_searchFragment_to_productInfoFragment,
                            bundle
                        )
                    }

                })
                binding.apply {
                    rvResultSearch.adapter = adapter
                }
            }
            message.observe(viewLifecycleOwner) {
                // Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                noItemText.text = it
            }
            isShowProgress.observe(viewLifecycleOwner) {
                showProgressBar(it)
            }

        }


    }

    private fun showProgressBar(isShow: Boolean) {
        if (isShow) {
            progressBar.show()
        } else progressBar.hide()
    }


}