package com.example.tptour.attractions.view

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.example.tptour.R
import com.example.tptour.attractions.adapter.AttractionClickInterface
import com.example.tptour.attractions.adapter.AttractionPagingAdapter
import com.example.tptour.attractions.adapter.ImageSliderAdapter
import com.example.tptour.base.GlideApp
import com.example.tptour.base.LanguageCode
import com.example.tptour.data.AttractionListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@BindingAdapter("imageItem")
fun bindRecyclerViewImagePager(pager: ViewPager2, imageItems: List<String>?) {
    imageItems?.let {
        pager.adapter = getOrCreateImagePagerListAdapter(pager, it)
        //set the orientation of the viewpager using ViewPager2.orientation
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 1
        pager.currentItem = currentPageIndex

        // registering for page change callback
        pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                }
            }
        )
    }
}

private fun getOrCreateImagePagerListAdapter(pager: ViewPager2, imageItems: List<String>): ImageSliderAdapter {
    return if (pager.adapter != null && pager.adapter is ImageSliderAdapter) {
        pager.adapter as ImageSliderAdapter
    } else {
        val adapter = ImageSliderAdapter(imageItems)
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        pager.adapter = adapter
        adapter
    }
}

@BindingAdapter("langCode")
fun setLangCode(recyclerView: RecyclerView, languageCode: LanguageCode?) {
    languageCode?.let {
        getOrCreateAttractionListAdapter(recyclerView).refresh()
    }
}

@BindingAdapter("attractionItems", "scope", requireAll = true)
fun bindRecyclerViewAttractionItemList(recyclerView: RecyclerView, dataItemList: PagingData<AttractionListItem>?, scope: CoroutineScope) {
    dataItemList?.let {
        scope.launch {
            getOrCreateAttractionListAdapter(recyclerView).submitData(dataItemList)
        }
    }
}

private fun getOrCreateAttractionListAdapter(recyclerView: RecyclerView): AttractionPagingAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is AttractionPagingAdapter) {
        (recyclerView.adapter as AttractionPagingAdapter).stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView.adapter as AttractionPagingAdapter
    } else {
        val adapter = AttractionPagingAdapter()
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView.adapter = adapter
        adapter
    }
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
    imageView.setImageResource(R.drawable.broken_image_fill0_wght400_grad0_opsz48)

    val circularProgressDrawable = CircularProgressDrawable(imageView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    imageUrl?.let {
        GlideApp.with(imageView.context).load(imageUrl)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.broken_image_fill0_wght400_grad0_opsz48)
            .into(imageView)
    }
}

@BindingAdapter("attractionClickInterface")
fun bindRecyclerViewWithInterface(recyclerView: RecyclerView, attractionClickInterface: AttractionClickInterface?) {
    attractionClickInterface?.let {
        getOrCreateAttractionListAdapter(recyclerView).attractionClickInterface = it
    }
}

@BindingAdapter("underLineText")
fun loadImageUrl(textView: TextView, webLink: String?) {
    val content = SpannableString(webLink)
    content.setSpan(UnderlineSpan(), 0, content.length, 0)
    textView.text = content
}