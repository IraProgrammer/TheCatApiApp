package com.example.myapplication.data.repositories

import com.example.myapplication.data.mappers.toModel
import com.example.myapplication.data.network.CatsApi
import com.example.myapplication.data.network.models.AddCat
import com.example.myapplication.domain.models.Cat
import com.example.myapplication.domain.repositories.CatsRepository
import io.reactivex.Single

class CatsRepositoryImpl(private val catsApi: CatsApi) :
    CatsRepository {

    override fun loadRandomCat(): Single<Cat> {
        return catsApi.getRandomCat()
            .map { it[0].toModel() }
    }

    override fun addToFavourite(imageId: String): Single<Any> {
        return catsApi.addCatToFavourites(AddCat(imageId))
    }
}