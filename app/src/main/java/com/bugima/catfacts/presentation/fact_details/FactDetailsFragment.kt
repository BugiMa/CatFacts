package com.bugima.catfacts.presentation.fact_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bugima.catfacts.R
import com.bugima.catfacts.presentation.MainActivity
import com.bugima.catfacts.databinding.FragmentFactDetailsBinding
import com.bugima.catfacts.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactDetailsFragment : Fragment() {

    private lateinit var factDetailsViewModelImpl: FactDetailsViewModelImpl

    private var _binding: FragmentFactDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: FactDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactDetailsBinding.inflate(inflater, container, false)
        factDetailsViewModelImpl = ViewModelProvider(requireActivity())[FactDetailsViewModelImpl::class.java]
        factDetailsViewModelImpl.loadFact(args.factId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        factDetailsViewModelImpl.fact.observe(viewLifecycleOwner) {
                resource ->
            when (resource) {
                is Resource.Success -> {
                    val fact = resource.data
                    (requireActivity() as MainActivity).supportActionBar?.title = fact.id
                    binding.progressBar.visibility = View.GONE
                    binding.factCard.visibility = View.VISIBLE
                    binding.detailsTextView.text = fact.text
                    binding.updateDateTextView.text = fact.updateDate
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.factCard.visibility = View.GONE
                    Toast.makeText(requireActivity(), getString(R.string.error_toast, resource.message), Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.factCard.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}