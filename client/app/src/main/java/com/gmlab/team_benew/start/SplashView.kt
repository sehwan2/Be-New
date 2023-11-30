package com.gmlab.team_benew.start

interface SplashView {

    fun onTokenCheckSuccess()
    // 토큰 유효성 실패시
    fun onTokenCheckFailure()
}