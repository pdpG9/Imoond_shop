package com.example.imoondshop.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imoondshop.R
import com.example.imoondshop.databinding.FragmentAccountBinding
import com.example.imoondshop.ui.adapter.RecyclerViewAdapter
import com.example.imoondshop.untils.showProgress
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private val vm by viewModel<AccountViewModel>()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        val k = arguments?.getBoolean("key", false)
        if (k == true) {
            findNavController().popBackStack(R.id.homeFragment, true)
        }
        binding.apply {
            listProduct.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                rvAdapter = RecyclerViewAdapter()
                adapter = rvAdapter
            }
        }
        vm.apply {
            lifecycleScope.launch {
                productsFlow?.collectLatest {

                    binding.itemProgress.progressBar.showProgress(false)
                    rvAdapter.submitData(it)
                }
            }
            message.observe(viewLifecycleOwner) {
                // binding.itemProgress.tvNoResult.text = it
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            isShowLoader.observe(viewLifecycleOwner) {
                binding.itemProgress.progressBar.showProgress(it)
            }
            toast.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

    }


}
