package com.example.admin.getdata



import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {

    @GET ("/api/people/1")
    fun getFirstPerson(): Call<StarWarsPerson>
}