package com.example.tptour.attractions.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tptour.attractions.adapter.AttractionClickInterface
import com.example.tptour.attractions.adapter.AttractionPagingAdapter
import com.example.tptour.base.LanguageCode
import com.example.tptour.base.SingleLiveEvent
import com.example.tptour.data.AttractionListItem
import com.example.tptour.repo.TourRepo
import com.example.tptour.repo.datasource.AttractionListDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AttractionListViewModel (application: Application) : AndroidViewModel(application) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _langCodeSetting = MutableLiveData<LanguageCode>()
    val langCodeSetting: LiveData<LanguageCode> = _langCodeSetting

    private val loadingInterface = object : AttractionListDataSource.PageLoadingInterface{
        override fun onPageLoading() {
            _isLoading.postValue(true)
        }

        override fun onLoadingFinish() {
            _isLoading.postValue(false)
        }
    }
    val scope: CoroutineScope get() = viewModelScope

    private val _attractionsDate = MutableLiveData<PagingData<AttractionListItem>>()
    val attractionsDate: LiveData<PagingData<AttractionListItem>> = _attractionsDate

    val attractionsDateDetail = SingleLiveEvent<AttractionListItem>()

    val attractionClickInterface = object : AttractionClickInterface {
        override fun onAttractionItemClick(attractionListItem: AttractionListItem) {
            attractionsDateDetail.value = attractionListItem
        }
    }
    val adapter = AttractionPagingAdapter()
    init {
        scope.launch {
            getData().cachedIn(viewModelScope).collect{
                _attractionsDate.value = it
            }
        }
    }

    private fun getData() : Flow<PagingData<AttractionListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {TourRepo.getAttractionsDatasource(loadingInterface)}
        ).flow
    }

    fun setLangCode(languageCode: LanguageCode){
        TourRepo.setLangCode(languageCode)
        _langCodeSetting.value = languageCode
    }
}