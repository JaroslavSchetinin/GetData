package com.example.admin.getdata

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StarWarsSimlpeFragment : Fragment() {

    private lateinit var nameAndGender: TextView
    private lateinit var heightAndMass: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.starwars_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameAndGender = view.findViewById(R.id.name_and_gender)
        heightAndMass = view.findViewById(R.id.height_and_mass)
    }

    fun bind(starWarsPerson: StarWarsPerson) {
        nameAndGender.text = "${starWarsPerson.name} (${starWarsPerson.gender})"
        heightAndMass.text = "Height: ${starWarsPerson.height} cm. Mass: ${starWarsPerson.mass} kg"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://swapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val starWarsApi = retrofit.create(StarWarsApi::class.java)

        starWarsApi.getFirstPerson().enqueue(object : Callback<StarWarsPerson> {
            override fun onFailure(call: Call<StarWarsPerson>?, t: Throwable?) {
                Log.e("TAG", "ERROR")
            }

            override fun onResponse(call: Call<StarWarsPerson>?, response: Response<StarWarsPerson>) {
                val starWarsPerson = response.body()
                if (starWarsPerson != null) {
                    bind(starWarsPerson)
                }
            }
        })
    }


}