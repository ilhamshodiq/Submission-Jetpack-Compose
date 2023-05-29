package com.the12smb.submissionjetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.jetreward.ui.common.UiState
import com.the12smb.submissionjetpackcompose.data.ContentRepository
import com.the12smb.submissionjetpackcompose.model.ContentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailContentViewModel(
    private val repository: ContentRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<ContentList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ContentList>>
        get() = _uiState

    fun getContentById(contentId: Long) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getContentListById(contentId))

    }
}