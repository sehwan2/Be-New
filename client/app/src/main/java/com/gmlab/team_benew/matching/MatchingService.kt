package com.gmlab.team_benew.matching


import android.content.Context
import com.gmlab.team_benew.auth.getRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchingService {

    private lateinit var matchingPostView: MatchingPostView


    fun setMatchingPostView(matchingPostView: MatchingPostView){
        this.matchingPostView = matchingPostView
    }

    private fun getUserData(context: Context, matchRequestDto: MatchRequestDto){
        val token = getTokenFromSharedPreferences(context) ?: return
        val bearerToken = "Bearer $token"
        val matchingPostService = getRetrofit().create(MatchingRetrofitInterface::class.java)


        matchingPostService.postCreateMatch(bearerToken, matchRequestDto).enqueue(object: Callback<MatchingResponse> {
            override fun onResponse(call: Call<MatchingResponse>, response: Response<MatchingResponse>)
            {
               when(response.code()) {
                   200 -> {
                       val responseBody = response.body()!!
                       val profile = responseBody.profile

                       //profile 정보
                       val profileInstruction = profile.instruction
                       val profilePhoto = profile.photo
                       val profileRole = profile.role
                       val profilePeer = profile.peer
                       val profileProjectExperience = profile.projectExperience

                       //member의 이름
                       val userName = profile.member.name
                   }
               }
            }

            override fun onFailure(call: Call<MatchingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("userToken", null)
    }

}