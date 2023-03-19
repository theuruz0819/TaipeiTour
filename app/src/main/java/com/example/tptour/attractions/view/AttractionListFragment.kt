package com.example.tptour.attractions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tptour.R
import com.example.tptour.attractions.view.AttractionDetailFragment.Companion.PAGE_TITLE
import com.example.tptour.attractions.viewmodel.AttractionListViewModel
import com.example.tptour.databinding.FragmentAttractionListBinding

class AttractionListFragment : Fragment() {
    private val viewModel: AttractionListViewModel by activityViewModels()
    private var binding: FragmentAttractionListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAttractionListBinding.inflate(inflater)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.attractionListViewModel = viewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attractionsDateDetail.observe(viewLifecycleOwner){
            it?.let{
                findNavController().navigate(R.id.fragmentAttractionDetail, bundleOf(PAGE_TITLE to it.name))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}