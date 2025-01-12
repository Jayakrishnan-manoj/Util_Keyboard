package com.jayk.utilkeyboard.data

import com.jayk.utilkeyboard.data.models.request.ChatRequest
import com.jayk.utilkeyboard.data.models.request.Message
import com.jayk.utilkeyboard.data.models.response.ChatResponse
import com.jayk.utilkeyboard.data.repository.APIRepository
import com.jayk.utilkeyboard.data.services.APIService
import javax.inject.Inject

class APIRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : APIRepository {
    override suspend fun getSearchResults(
        model: String,
        messages: List<Message>
    ): ApiResult<ChatResponse> {
        return try {
            val response = apiService.getSearchResults(
                "Bearer ",
                ChatRequest(messages, model)
            )
            ApiResult.Success(response)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "unknown error occurred")
        }
    }

}