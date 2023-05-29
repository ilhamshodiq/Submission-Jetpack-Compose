package com.the12smb.submissionjetpackcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.jetreward.ui.common.UiState
import com.the12smb.submissionjetpackcompose.data.ContentRepository
import com.the12smb.submissionjetpackcompose.model.Content
import com.the12smb.submissionjetpackcompose.model.ContentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ContentRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ContentList>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ContentList>>>
        get() = _uiState

   fun getAllContents() {
        viewModelScope.launch {
            repository.getAllContents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { contents ->
                    _uiState.value = UiState.Success(contents)
                }
        }
    }
}