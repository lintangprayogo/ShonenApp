package com.example.shonenapp.data.remote

import com.example.shonenapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ShonenApiService {
    @GET("/getAllCharacters")
    suspend fun getAllShonenCharacters(@Query("page") page:Int): ApiResponse

    @GET("/search-characters")
    suspend fun searchShonenCharacters(@Query("page") page:Int,@Query("name") name:String) : ApiResponse

}