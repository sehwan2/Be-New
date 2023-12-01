package com.gmlab.team_benew.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.auth.LoginActivity
import com.gmlab.team_benew.auth.SignUpActivity
import com.gmlab.team_benew.auth.register.RegisterActivity
import com.gmlab.team_benew.databinding.ActivityIntroBinding

class IntroActivity:AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIntroLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnIntroRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
}