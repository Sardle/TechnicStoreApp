package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.mappers.NewsMapper
import com.example.technicstoreapp.data.mappers.TechnicMapper
import com.example.technicstoreapp.data.network.NewsService
import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.NewsData
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mapperTechnic: TechnicMapper,
    private val server: Server,
    private val mapperNews: NewsMapper,
    private val prefs: UserDataSource,
    private val service: NewsService
) : Repository {

    override fun getAllTechnic(): List<TechnicData> {
        return server.getAllTechnic().map { mapperTechnic(it) }
    }

    override suspend fun getNews(): List<NewsData> {
        return withContext(Dispatchers.IO) {
            service.getNews("pc technologies", prefs.getUserToken())
                .execute()
                .body()?.let { mapperNews(it) } ?: throw Exception()
        }
    }

    override fun setUserToken(token: String) {
        prefs.setUserToken(token)
    }
}