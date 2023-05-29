package com.the12smb.submissionjetpackcompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.jetreward.ui.common.UiState
import com.the12smb.submissionjetpackcompose.R
import com.the12smb.submissionjetpackcompose.data.ContentRepository
import com.the12smb.submissionjetpackcompose.di.Injection
import com.the12smb.submissionjetpackcompose.ui.ViewModelFactory
import com.the12smb.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun DetailScreen(
    contentId: Long,
    viewModel: DetailContentViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getContentById(contentId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.content.id,
                    data.content.name,
                    data.content.type,
                    data.content.complexity,
                    data.content.role,
                    data.content.lore,
                    data.content.photo,
                    onBackClick = navigateBack,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    id: Long,
    name: String,
    type: String,
    complexity: String,
    role: String,
    lore: String,
    @DrawableRes photo: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(photo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(
                        text = type,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = MaterialTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = complexity,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = MaterialTheme.colors.secondary
                    )
                }
                Text(
                    text = role,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary
                )
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray))
            Column(
                modifier = Modifier.padding(all = 8.dp),
            ) {
                Text(
                    text = lore,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                )
            }

        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    SubmissionJetpackComposeTheme() {
        DetailContent(
            1212,
            "Hoodwink",
            "Ranged",
            "Angel",
            "Support",
            "asdasdas",
            R.drawable.aa,
            onBackClick = {},
        )
    }
}