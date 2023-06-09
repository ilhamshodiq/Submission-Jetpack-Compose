package com.the12smb.submissionjetpackcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.jetreward.ui.common.UiState
import com.the12smb.submissionjetpackcompose.data.ContentRepository
import com.the12smb.submissionjetpackcompose.di.Injection
import com.the12smb.submissionjetpackcompose.model.Content
import com.the12smb.submissionjetpackcompose.model.ContentList
import com.the12smb.submissionjetpackcompose.ui.ViewModelFactory
import com.the12smb.submissionjetpackcompose.ui.components.ContentItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllContents()
            }
            is UiState.Success -> {
                HomeContent(
                    contents = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    contents: List<ContentList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(contents) { data ->
            ContentItem(
                image = data.content.photo,
                name =  data.content.name,
                type = data.content.type,
                modifier = Modifier.clickable {
                    navigateToDetail(data.content.id)
                }
            )
        }
    }
}