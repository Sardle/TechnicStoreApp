package com.example.technicstoreapp.data.mappers

import com.example.technicstoreapp.data.models.NewsArticlesResponse
import com.example.technicstoreapp.domain.NewsData
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    operator fun invoke(response: NewsArticlesResponse): List<NewsData> {
        return response.articles?.map { article ->
            NewsData(
                author = article.author.orEmpty(),
                title = article.title.orEmpty(),
                url = article.url.orEmpty(),
                urlToImage = article.urlToImage.orEmpty()
            )
        } ?: emptyList()
    }
}