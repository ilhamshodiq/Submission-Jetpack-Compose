package com.the12smb.submissionjetpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.the12smb.submissionjetpackcompose.data.ContentRepository
import com.the12smb.submissionjetpackcompose.ui.screen.detail.DetailContentViewModel
import com.the12smb.submissionjetpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ContentRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailContentViewModel::class.java)) {
            return DetailContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}