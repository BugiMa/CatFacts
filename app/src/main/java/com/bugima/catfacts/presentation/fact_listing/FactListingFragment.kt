package com.bugima.catfacts.presentation.fact_listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugima.catfacts.presentation.MainActivity
import com.bugima.catfacts.R
import com.bugima.catfacts.databinding.FragmentFactListingBinding
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactListingFragment : Fragment() {

    private lateinit var factListingViewModel: FactListingViewModel

    private var _binding: FragmentFactListingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        factListingViewModel = ViewModelProvider(requireActivity())[FactListingViewModel::class.java]

        val factListingRecyclerViewAdapter = FactListingRecyclerViewAdapter(emptyList())

        binding.factRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = factListingRecyclerViewAdapter
        }

        factListingViewModel.facts.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    factListingRecyclerViewAdapter.updateData(resource.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireActivity(), "Error: ${resource.message}", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.refreshFab.setOnClickListener {
            factListingViewModel.loadFacts()
            Toast.makeText(requireActivity(),"Loading more facts", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}