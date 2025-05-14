package com.example.neotube.ui.trending


import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.neotube.model.VideoItem
import com.example.neotube.ui.component.VideoThumbnailCard
import com.example.neotube.ui.trending.TrendingViewModel.TrendingIntent
import com.example.neotube.ui.trending.TrendingViewModel.TrendingUiState

@Composable
fun TrendingScreen(viewmodel: TrendingViewModel){
    val onVideoItemClick = { video: VideoItem -> viewmodel.processIntent(TrendingIntent.SelectVideo(video))}
    val uiState by viewmodel.uiState.collectAsState()
    // 화면이 처음 나타날 때 LoadTrending Intent 보내기
    LaunchedEffect(Unit) {
        viewmodel.processIntent(TrendingIntent.LoadTrending)
    }
    when (uiState) {
        is TrendingUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is TrendingUiState.Success -> {
            TrendingList(videos = (uiState as TrendingUiState.Success).videos, onVideoItemClick = onVideoItemClick)
        }
        is TrendingUiState.Error -> {
            // 에러 메시지나 재시도 버튼
            val message = (uiState as TrendingUiState.Error).errorMessage
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("에러 발생: $message")
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { viewmodel.processIntent(TrendingIntent.Retry) }) {
                        Text("다시 시도")
                    }
                }
            }
        }
    }
}


@Composable
private fun TrendingList(
    videos: List<VideoItem>,
    onVideoItemClick: (VideoItem) -> Unit
) {
    LazyColumn {
        items(items = videos, key = { it.id }) { video ->
            VideoThumbnailCard(
                thumbnailRes = R.drawable.sym_contact_card,
                title        = video.title,
                channel      = video.channelName,
                views        = video.views,
                onClick      = { onVideoItemClick(video) }
            )
        }
    }
}

