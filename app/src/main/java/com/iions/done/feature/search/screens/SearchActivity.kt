package com.iions.done.feature.search.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.iions.done.R
import com.iions.done.base.BaseActivity
import com.iions.done.databinding.ActivitySearchBinding
import com.paulrybitskyi.persistentsearchview.utils.SuggestionCreationUtil
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding.searchView) {
            setOnLeftBtnClickListener {
                onBackPressed()
            }
            setOnClearInputBtnClickListener {
                // Handle the clear input button click
            }

            // Setting a delegate for the voice recognition input
            setVoiceRecognitionDelegate(VoiceRecognitionDelegate(this@SearchActivity))

            setOnSearchConfirmedListener { searchView, query ->
                // Handle a search confirmation. This is the place where you'd
                // want to perform a search against your data provider.
            }

            // Disabling the suggestions since they are unused in
            // the simple implementation
            setSuggestionsDisabled(true)
        }
    }

    override fun layout() = R.layout.activity_search

    override fun initObservers() {
    }

//    override fun onResume() {
//        super.onResume()
//
//        // Fetching the search queries from the data provider
//        val searchQueries = if (binding.searchView.isInputQueryEmpty) {
//            mDataProvider.getInitialSearchQueries()
//        } else {
//            mDataProvider.getSuggestionsForQuery(binding.searchView.inputQuery)
//        }
//
//        // Converting them to recent suggestions and setting them to the widget
//        binding.searchView.setSuggestions(
//            SuggestionCreationUtil.asRecentSearchSuggestions(
//                searchQueries
//            ), false
//        )
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        VoiceRecognitionDelegate.handleResult(binding.searchView, requestCode, resultCode, data)
    }
}