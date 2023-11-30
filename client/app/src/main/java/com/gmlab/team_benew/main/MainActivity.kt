package com.gmlab.team_benew.main

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gmlab.team_benew.R
import com.gmlab.team_benew.start.SplashAuthService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() { //compat 호환성을 해준다는 이야기

    //lifecycle 콜백함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //사용자에게 보여줄 레이아웃 선정 파일 ID인수로
        setSupportActionBar(findViewById(R.id.toolbar_app_default))
        // 툴바 제목 설정 제거
        supportActionBar?.title = ""

        Log.d(TAG, "onCreate")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_main)
        bottomNavigationView.itemIconTintList = null

        val navController = supportFragmentManager.findFragmentById(R.id.Fragment_container)
            ?.findNavController() // 참조를 반환, find or get 존재하지않을수 있으니 safe call 컨트롤러
        navController?.let {
            bottomNavigationView.setupWithNavController(it) //navHostFragment에서 관리하는 controller
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                // 알림창 모달 띄우기
                showAlertDialog()
                true
            }

            R.id.item2 -> {
                // 채팅 리스트 프래그먼트로 이동
                findNavController(R.id.Fragment_container).navigate(R.id.action_home_to_chatList)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlertDialog()
    {
        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage("알림창입니다.")
            .setPositiveButton("확인") { dialog, which ->
                // "확인" 버튼 클릭 시 수행할 동작
            }
            .setNegativeButton("취소") { dialog, which ->
                // "취소" 버튼 클릭 시 수행할 동작
            }
            .show()
    }


}