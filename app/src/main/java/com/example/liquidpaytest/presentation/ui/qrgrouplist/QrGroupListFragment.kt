package com.example.liquidpaytest.presentation.ui.qrgrouplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.liquidpaytest.databinding.FragmentQrGroupListBinding
import com.example.liquidpaytest.presentation.core.UiState
import com.example.liquidpaytest.presentation.ui.qrgrouplist.adapter.QrGroupListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class QrGroupListFragment : Fragment() {

    private var _binding: FragmentQrGroupListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QrGroupListViewModel by viewModel()

    private val qrGroupAdapter: QrGroupListAdapter = QrGroupListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrGroupListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.qrGroupRv.adapter = qrGroupAdapter
        launchWhenResumed {
            viewModel.uiState.collectLatest {
                when (it) {
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
        launchWhenResumed {
            viewModel.qrGroupDatasource.collectLatest {
                qrGroupAdapter.setItemList(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Ext function for fragment
fun Fragment.launchWhenResumed(block: suspend CoroutineScope.() -> Unit): Job {
    return viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED, block)
    }
}
