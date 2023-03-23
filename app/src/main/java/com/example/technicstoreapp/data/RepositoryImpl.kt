package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.mappers.DataBaseMapper
import com.example.technicstoreapp.data.mappers.NewsMapper
import com.example.technicstoreapp.data.mappers.TechnicMapper
import com.example.technicstoreapp.data.network.NewsService
import com.example.technicstoreapp.data.source.DataBaseSource
import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.NewsData
import com.example.technicstoreapp.domain.Repository
import com.example.technicstoreapp.domain.TechnicData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mapperTechnic: TechnicMapper,
    private val server: Server,
    private val mapperNews: NewsMapper,
    private val prefs: UserDataSource,
    private val service: NewsService,
    private val db: DataBaseSource,
    private val mapperDb: DataBaseMapper
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

    override fun getCategories(): List<String> = server.getCategories()
    override fun getTechnicBasedFromCategory(category: String): List<TechnicData> =
        server.getAllTechnic().filter { it.category == category }.map { mapperTechnic(it) }

    override suspend fun insertTechnic(technicData: TechnicData) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(technicData.id)
            if (existingTechnic != null) {
                with(existingTechnic) {
                    count += 1
                    currentPrice = price * count
                }
                db.updateItem(existingTechnic)
            } else {
                db.insert(mapperDb.dataToEntity(technicData, "orange", 1, technicData.price))
            }
        }
    }

    override suspend fun getAllTechnicFromCart(): List<CartTechnicData> {
        return withContext(Dispatchers.IO) {
            db.getAll().map { mapperDb.entityToCartData(it) }
        }
    }

    override suspend fun deleteAllTechnicFromCart(listTechnic: List<CartTechnicData>) {
//        withContext(Dispatchers.IO) {
//            db.deleteUnitTechnic(listTechnic.map { mapperDb.dataToEntity(it) })
//        }
    }

    override fun getTechnicInfo(id: Int): TechnicData =
        server.getAllTechnic().filter { it.id == id }.map { mapperTechnic(it) }.first()

    override suspend fun removeUnitTechnic(technicData: TechnicData) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(technicData.id)
            if (existingTechnic != null) {
                with(existingTechnic) {
                    count -= 1
                    currentPrice = price * count
                }
                db.updateItem(existingTechnic)
            }
        }
    }

    override suspend fun deleteTechnic(technicData: TechnicData) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(technicData.id)
            if (existingTechnic != null) {
                db.deleteTechnic(existingTechnic)
            }
        }
    }

    override suspend fun getSumCurrentPrices(): Double = withContext(Dispatchers.IO) {
        db.getCurrentPrices().sum()
    }

    override fun getSearchResult(searchString: String): List<TechnicData> {
        return server.getAllTechnic().map { mapperTechnic(it) }.filter {
            it.name.lowercase(Locale.ROOT)
                .contains(searchString.lowercase(Locale.ROOT).toRegex())
        }
    }
}