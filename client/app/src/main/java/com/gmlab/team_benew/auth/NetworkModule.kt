package com.gmlab.team_benew.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//만약 AuthRetrofitInterface에 /를 빼고 선언했다면 BASE_URL에는 /를 붙여줘야한다.
const val BASE_URL = "http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com"

//해당 함수는 Retrofit을 반환한다.
//Retrofit 객체 생성 시 구글이 만든 Gson을 이용하여 json을 자바 코드로 변환한다. 존나강력!
fun getRetrofit(): Retrofit {
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    return retrofit
}