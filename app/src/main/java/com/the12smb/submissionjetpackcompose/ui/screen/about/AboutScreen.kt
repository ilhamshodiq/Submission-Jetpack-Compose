package com.the12smb.submissionjetpackcompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.the12smb.submissionjetpackcompose.R
import com.the12smb.submissionjetpackcompose.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier.background(color = Color.Gray),
    onBackClick:() -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { onBackClick() }
                )
                Text(
                    text = "About Me",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(1f).padding(top=4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(48.dp))
                    .height(300.dp)
                    .padding(16.dp),
                painter = painterResource(R.drawable.profl),
                contentDescription = "profile"
            )
            Text(
                text = "Ilham Shodiq",
                fontWeight = FontWeight.SemiBold,
                fontSize = 38.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ilhambheh@gmail.com",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "kata-kata motivasi",
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SubmissionJetpackComposeTheme {
        AboutScreen(onBackClick = {})
    }
}