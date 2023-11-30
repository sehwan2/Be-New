package com.gmlab.team_benew.start

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gmlab.team_benew.auth.AuthResponse
import com.gmlab.team_benew.auth.AuthRetrofitInterface
import com.gmlab.team_benew.auth.LoginActivity
import com.gmlab.team_benew.auth.TokenGet
import com.gmlab.team_benew.auth.User
import com.gmlab.team_benew.auth.getRetrofit
import com.gmlab.team_benew.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashAuthService(private  val context: Context) {

    private lateinit var splashView: SplashView

    fun setSplashView(splashView: SplashView) {
        this.splashView = splashView
    }


    fun verifyUserToken(token: String, account: String)
    {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        val bearerToken = "Bearer $token"
        authService.adminGet(bearerToken, account).enqueue(object : Callback<TokenGet> {
            override fun onResponse(call: Call<TokenGet>, response: Response<TokenGet>) {
                Log.d("NETWORK_VERIFY_USER/SUCCESS","USER_TOKEN_VAILD")
                when(response.code()) {
                    200 -> {
                        val serverToken = response.body()?.token
                        val localToken = getTokenFromSharedPreferences(context)
                        if (serverToken != null && serverToken == localToken) {
                            splashView.onTokenCheckSuccess()
                            //
                        }
                    }

                    401 -> {
                        splashView.onTokenCheckFailure()
                    }
                    else -> {
                        response.code()
                    }
                }
            }

            override fun onFailure(call: Call<TokenGet>, t: Throwable) {
                Log.d("NETWORK_VERIFY_USER/FAILURE" , "스플래쉬 네트워크 오류 에러")
            }
        })
    }
    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("userToken", null)
    }
}