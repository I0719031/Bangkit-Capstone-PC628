package com.dicoding.lecker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerView)
        recipeAdapter = RecipeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeAdapter

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://leckerscapstone12-gjlu4leqjq-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ApiService
        apiService = retrofit.create(ApiService::class.java)

        // Set click listener for the button to send the text
        val btnSend: Button = findViewById(R.id.btnSend)
        btnSend.setOnClickListener {
            val editText: EditText = findViewById(R.id.editText)
            val inputText = editText.text.toString()

            // Create a JSON string with the input text
            val json = "{\"Ingredient\":\"$inputText\"}"

            // Create a RequestBody from the JSON string
            val requestBody = RequestBody.create(MediaType.parse("application/json"), json)

            // Make the API call
            apiService.createPost(requestBody).enqueue(object : Callback<RecipeData> {
                override fun onResponse(call: Call<RecipeData>, response: Response<RecipeData>) {
                    if (response.isSuccessful) {
                        val recipeData = response.body()
                        if (recipeData != null) {
                            recipeAdapter.updateData(recipeData.result)
                        }
                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<RecipeData>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}

