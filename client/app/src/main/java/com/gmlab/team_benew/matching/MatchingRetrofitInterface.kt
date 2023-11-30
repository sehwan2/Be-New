package com.gmlab.team_benew.matching

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
interface MatchingRetrofitInterface {
    @POST("/api/post/match")
    fun postCreateMatch(@Header("Authorization") bearerToken: String, @Body matchRequestDto: MatchRequestDto): Call<MatchingResponse>
}