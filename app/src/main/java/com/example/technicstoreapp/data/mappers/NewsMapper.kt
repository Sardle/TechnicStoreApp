package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.NewsResponse
import com.example.technicstoreapp.domain.models.NewsData
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    operator fun invoke(response: NewsResponse): NewsData = with(response) {
        NewsData(
            author = author.orEmpty(),
            title = title.orEmpty(),
            url = url.orEmpty(),
            urlToImage = urlToImage.orEmpty()
        )
    }
}