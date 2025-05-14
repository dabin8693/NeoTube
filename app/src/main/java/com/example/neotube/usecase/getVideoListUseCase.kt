package com.example.neotube.usecase

import com.example.neotube.model.VideoItem
import com.example.neotube.model.Result
import com.example.neotube.model.video.VideoRepositoryImpl

class getVideoListUseCase(val videoRepository:VideoRepositoryImpl) {
//    operator fun invoke(url: String): Flow<List<VideoItem>> {
//        val dummyList = List(10) { index -> VideoItem("id:$index", "Demo Video Title", "Sample Channel", "1.2K", "www.abc.com", emptyList()) }
//        return MutableStateFlow(dummyList)
//    }
    suspend operator fun invoke(url: String): Result<List<VideoItem>> {
        val dummyList = List(10) { index -> VideoItem("id:$index", "Demo Video Title", "Sample Channel", "1.2K", "www.abc.com", emptyList()) }
        return try {
            Result.Success(dummyList)
        }catch (e: Exception){
            Result.Error(e)
        }
    }
}
