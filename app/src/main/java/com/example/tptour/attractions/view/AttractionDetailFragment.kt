package com.example.tptour.attractions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tptour.R
import com.example.tptour.attractions.viewmodel.AttractionListViewModel
import com.example.tptour.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment : Fragment() {

    private var binding: FragmentAttractionDetailBinding? = null
    private val viewModel: AttractionListViewModel by activityViewModels()

    companion object{
        const val PAGE_TITLE = "page_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAttractionDetailBinding.inflate(inflater)
        binding?.attractionViewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.officialLinkText?.setOnClickListener {
            startActivity(viewModel.attractionsDateDetail.value?.official_site?.let { it1 ->
                context?.let { it2 ->
                    WebViewActivity.newIntent(
                        it2,
                        it1
                    )
                }
            })
        }
    }

}