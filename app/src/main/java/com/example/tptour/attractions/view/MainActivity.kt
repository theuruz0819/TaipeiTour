package com.example.tptour.attractions.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tptour.R
import com.example.tptour.attractions.view.AttractionDetailFragment.Companion.PAGE_TITLE
import com.example.tptour.attractions.viewmodel.AttractionListViewModel
import com.example.tptour.base.LanguageCode
import com.example.tptour.databinding.ActivityMainBinding
import com.example.tptour.repo.TourRepo

class MainActivity : AppCompatActivity() {

    private val viewModel: AttractionListViewModel by viewModels()
    private val navController: NavController by lazy {
        findNavController(R.id.nav_main_page)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.langBtn.text = TourRepo.getLangCodeDisplayName()

        val items = LanguageCode.values()
        val displayItems = items.map { it.displayName }.toTypedArray()
        val selectedListener = DialogInterface.OnClickListener { _, which ->
            viewModel.setLangCode(items[which])
            binding.langBtn.text = TourRepo.getLangCodeDisplayName()
        }

        binding.langBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setCancelable(true)
                .setItems(displayItems, selectedListener)
                .show()
        }
        binding.mainPageTitleBack.setOnClickListener {
            navController.popBackStack()
        }
        navController.addOnDestinationChangedListener { _, destination, argument ->
            when(destination.id){
                R.id.fragmentAttractionList -> {
                    binding.mainPageTitleTextView.text = destination.label
                    binding.langBtn.visibility = View.VISIBLE
                    binding.mainPageTitleBack.visibility = View.GONE
                }
                R.id.fragmentAttractionDetail -> {
                    binding.mainPageTitleTextView.text = argument?.getString(PAGE_TITLE)
                    binding.langBtn.visibility = View.INVISIBLE
                    binding.mainPageTitleBack.visibility = View.VISIBLE
                }
                else ->{

                }
            }
        }
    }

}