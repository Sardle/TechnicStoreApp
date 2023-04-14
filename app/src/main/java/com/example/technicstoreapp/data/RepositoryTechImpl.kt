package com.example.technicstoreapp.data

import com.example.technicstoreapp.data.mappers.DataBaseMapper
import com.example.technicstoreapp.data.mappers.NewsMapper
import com.example.technicstoreapp.data.mappers.TechnicMapper
import com.example.technicstoreapp.data.network.NewsService
import com.example.technicstoreapp.data.network.TechService
import com.example.technicstoreapp.data.source.DataBaseSource
import com.example.technicstoreapp.data.source.UserDataSource
import com.example.technicstoreapp.domain.CartTechnicData
import com.example.technicstoreapp.domain.NewsData
import com.example.technicstoreapp.domain.RepositoryTech
import com.example.technicstoreapp.domain.TechnicData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryTechImpl @Inject constructor(
    private val mapperTechnic: TechnicMapper,
    private val mapperNews: NewsMapper,
    private val prefs: UserDataSource,
    private val service: NewsService,
    private val db: DataBaseSource,
    private val mapperDb: DataBaseMapper,
    private val techService: TechService
) : RepositoryTech {

    override suspend fun getAllTechnic(): List<TechnicData> {
        return withContext(Dispatchers.IO) {
            techService.getAllTechnic().map { mapperTechnic(it) }
        }
    }

    override suspend fun getNews(): List<NewsData> {
        return withContext(Dispatchers.IO) {
            service.getNews(QUERY, prefs.getUserToken()).articles?.map {
                mapperNews(it)
            } ?: emptyList()
        }
    }

    override fun setUserToken(token: String) {
        prefs.setUserToken(token)
    }

    override suspend fun getCategories(): List<String> {
        return withContext(Dispatchers.IO) {
            techService.getCategories()
        }
    }

    override suspend fun getTechnicBasedFromCategory(category: String): List<TechnicData> {
        return withContext(Dispatchers.IO) {
            techService.getTechnicByCategory(category).map { mapperTechnic(it) }
        }
    }

    override suspend fun plusUnitTechnic(id: Int, color: String) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(id, color)
            if (existingTechnic != null) {
                with(existingTechnic) {
                    count += 1
                    currentPrice = price * count
                }
                db.updateItem(existingTechnic)
            }
        }
    }

    override suspend fun insertTechnic(technicData: TechnicData, color: String) {
        withContext(Dispatchers.IO) {
            plusUnitTechnic(technicData.id, color)
            db.insert(
                mapperDb.dataToEntity(
                    technicData,
                    color,
                    1,
                    technicData.price,
                    technicData.colors[color].toString()
                )
            )
        }
    }

    override suspend fun getAllTechnicFromCart(): List<CartTechnicData> {
        return withContext(Dispatchers.IO) {
            db.getAll().map { mapperDb.entityToData(it) }
        }
    }

    override suspend fun deleteAllTechnicFromCart() {
        withContext(Dispatchers.IO) {
            db.delete(db.getAll())
        }
    }

    override suspend fun getTechnicInfo(id: Int): TechnicData {
        return withContext(Dispatchers.IO) {
            mapperTechnic(techService.getTechnicById(id))
        }
    }

    override suspend fun removeUnitTechnic(id: Int, color: String) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(id, color)
            if (existingTechnic != null) {
                with(existingTechnic) {
                    count -= 1
                    currentPrice = price * count
                }
                db.updateItem(existingTechnic)
            }
        }
    }

    override suspend fun deleteTechnic(id: Int, color: String) {
        withContext(Dispatchers.IO) {
            val existingTechnic = db.getItemById(id, color)
            if (existingTechnic != null) {
                db.deleteTechnic(existingTechnic)
            }
        }
    }

    override suspend fun getSumCurrentPrices(): Double = withContext(Dispatchers.IO) {
        db.getCurrentPrices().sum()
    }

    override suspend fun getSearchResult(searchString: String): List<TechnicData> {
        return withContext(Dispatchers.IO) {
            techService.search(searchString).map { mapperTechnic(it) }
        }
    }

    override suspend fun checkListCart(): Boolean {
        return withContext(Dispatchers.IO) {
            db.getAll().isEmpty()
        }
    }

    override suspend fun checkIfElementExists(name: String, color: String): Boolean {
        return withContext(Dispatchers.IO) {
            db.checkIfElementExists(name, color) == 0
        }
    }

    companion object {
        private const val QUERY = "pc technologies"
    }
}