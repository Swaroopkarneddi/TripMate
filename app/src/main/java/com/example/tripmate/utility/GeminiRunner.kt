package com.example.tripmate.utility

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content

class GeminiRunner {
    companion object {
        private val GMN_KEYS: List<String> = listOf("AIzaSyDDIpldmK2ASTjgZbYuLsdn0JSTSaBYMS0", "AIzaSyAMuJfKwuKDBMl3-hVJI1K5suOBljN0XPs", "AIzaSyBvOKfmXHUqrmwzS7uTSNQUNxUFfX3KMtk", "AIzaSyCLB9PEjNT9Rqzo7805OqL4ddRJowNxd9k", "AIzaSyCjAivvfgZa2_UG5Vs3P_sAdxtUmllQE8U")
        private lateinit var generativeModel: GenerativeModel

        private var geminiKeyIndex = 0

        private fun incrementGeminiKeyIndex() {
            geminiKeyIndex = (geminiKeyIndex + 1) % GMN_KEYS.size
        }

        suspend fun getPackages(destinationName: String?): String {
            incrementGeminiKeyIndex()
            generativeModel = GenerativeModel(
                modelName = "gemini-1.5-pro",
                apiKey = GMN_KEYS[geminiKeyIndex]
            )
            val prompt =
                """
                    data class Destination2 (
                        val name: String, 
                        val imageResId: Int, 
                        val price: String
                   ) 
                   this is my data class, 
                   I want data in this format, 
                   leave imageResId for all the different packages available for location $destinationName, 
                   give me only the package name and prices as a json object no other text also price should be in INR, 
                   give me only the json data and no extra text
                """.trimMargin()

            val promptContent = content {
                text(prompt)
            }

            try {
                val response: GenerateContentResponse = generativeModel.generateContent(promptContent)
                return response.text.toString()
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
                return e.message.toString()
            }
        }

        suspend fun getTravelPlan(destinationName: String?, packageName: String?, startingPlace: String): String {
            val prompt: String = """
                data class DayPlan(
                    val day: String,
                    val plan: String,
                    val flightPlan: String,
                    val flightDetails: String,
                    val flightTiming: String,
                    val transferPlan: String,
                    val transferDetails: String,
                    val transferDescription: String,
                    val hotelPlan: String,
                    val hotelName: String,
                    val hotelDescription: String,
                    val activityPlan: String,
                    val activityName: String,
                    val activityDescription: String,
                    val menuPlan: String,
                    val menuDetails: String,
                    val menuDescription: String
                )
                
                This is the dayplan format, give me day wise plans of $packageName in $destinationName $startingPlace, for attribute day, the value should be in the format Day <number>: <place-name-on-that-day>, if something is not required just give an empty string for that as value, ie give me an array of dayplan objects in json format, give me only the json data and no extra text
            """.trimIndent()

            val promptContent = content {
                text(prompt)
            }

            try {
                val response: GenerateContentResponse = generativeModel.generateContent(promptContent)
                Log.d("response", response.text.toString())
                return response.text.toString()
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
                return e.message.toString()
            }
        }
    }
}