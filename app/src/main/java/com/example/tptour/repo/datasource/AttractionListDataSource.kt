package com.example.tptour.repo.datasource

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.tptour.base.PreferencesManager
import com.example.tptour.data.AttractionListItem
import com.example.tptour.data.AttractionListResponse
import com.example.tptour.network.Apis
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AttractionListDataSource(
    private val apis: Apis,
    private val preferencesManager: PreferencesManager,
    private val pageLoadingInterface: PageLoadingInterface?): RxPagingSource<Int, AttractionListItem>() {

    interface PageLoadingInterface{
        fun onPageLoading()
        fun onLoadingFinish()
    }

    override fun getRefreshKey(state: PagingState<Int, AttractionListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, AttractionListItem>> {
        val page = params.key ?: 1
        return apis.getAttractions( preferencesManager.getRequestLangCode(), page).subscribeOn(Schedulers.io())
            .doOnSubscribe {
                pageLoadingInterface?.onPageLoading()
            }
            .map {
                pageLoadingInterface?.onLoadingFinish()
                toLoadResult(it, page)
            }
            .onErrorReturn {
                pageLoadingInterface?.onLoadingFinish()
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        response: AttractionListResponse,
        page: Int
    ): LoadResult<Int, AttractionListItem> {
        return LoadResult.Page(
            data = response.data,
            prevKey = if (page <= 1) null else page - 1,
            nextKey = if (response.data.isEmpty()) null else page + 1
        )
    }
}