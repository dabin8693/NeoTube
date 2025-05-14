package com.example.neotube.ui.trending

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neotube.model.VideoItem
import com.example.neotube.model.successOr
import com.example.neotube.usecase.getVideoListUseCase
import com.example.neotube.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.neotube.model.Result
import kotlinx.coroutines.flow.update

// Trending탭 프레그먼트 ViewModel
class TrendingViewModel(val getVideoListUseCase: getVideoListUseCase) : ViewModel() {
    /*
     * TrendList를 받으면 Success전달 콜백에서 Item 터치 활성화
     * 썸네일 로딩여부는 Glide가 책임짐
     */
    sealed interface TrendingUiState {
        object Loading : TrendingUiState
        data class Success(
            val videos: List<VideoItem>,
            // 프리뷰 재생할 영상
            val selected: VideoItem?
        ) : TrendingUiState
        data class Error(val errorMessage: String) : TrendingUiState
    }

    sealed class TrendingIntent {
        object LoadTrending : TrendingIntent() // 뷰가 처음 준비됐을때
        data class SelectVideo(val video: VideoItem) : TrendingIntent()
        object Retry : TrendingIntent()
    }

    // UI state 초기화
    private val _uiState = MutableStateFlow<TrendingUiState>(TrendingUiState.Loading)
    val uiState: StateFlow<TrendingUiState> = _uiState.asStateFlow()

    // UI 필요한 데이터 가져옴
    init {
        getVideoList()
    }

    fun getVideoList(){
        viewModelScope.launch {
            val result = getVideoListUseCase("testurl")
            val videoList = result.successOr(emptyList())
            _uiState.update {
                when(result){
                    is Result.Success -> TrendingUiState.Success(videoList, null)
                    is Result.Error -> TrendingUiState.Error(R.string.trending_load_error.toString())
                }
            }

        }
    }

    fun processIntent(trendingIntent: TrendingIntent){
        when(trendingIntent) {
            is TrendingIntent.LoadTrending -> { }
            // 클릭이 아닌 상단에서 3번째 아이템을 자동 선택(난중에 수정)
            is TrendingIntent.SelectVideo -> _uiState.update {
                (it as TrendingUiState.Success).copy(selected = trendingIntent.video)
            }
            is TrendingIntent.Retry -> getVideoList()
        }
    }
}