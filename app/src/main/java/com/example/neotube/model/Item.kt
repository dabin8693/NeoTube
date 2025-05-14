package com.example.neotube.model

data class VideoItem(
    val id: String,
    val title: String,
    val channelName: String,
    val views: String,
    val thumbnailUrl: String,
    val commentList: List<CommentItem>
)

data class CommentItem(
    val id: String,
    val text: String,
    val author: String,
)