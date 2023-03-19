package com.example.tptour.repo

import com.example.tptour.base.LanguageCode
import com.example.tptour.base.PreferencesManager
import com.example.tptour.network.Apis
import com.example.tptour.repo.datasource.AttractionListDataSource
import androidx.paging.InvalidatingPagingSourceFactory

object TourRepo {
    private lateinit var api: Apis
    private lateinit var preferencesManager: PreferencesManager

    fun initialize(apis: Apis, preferencesManager: PreferencesManager) {
        this.api = apis
        this.preferencesManager = preferencesManager
    }

    fun getAttractionsDatasource(loadingInterface: AttractionListDataSource.PageLoadingInterface): AttractionListDataSource {
        return AttractionListDataSource(api, preferencesManager, loadingInterface)
    }

    fun setLangCode(languageCode: LanguageCode) {
        preferencesManager.setLangCode(languageCode.name)
    }

    fun getLangCodeDisplayName(): String{
        return LanguageCode.valueOf(preferencesManager.getLangCode()).displayName
    }
}