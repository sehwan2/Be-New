package com.gmlab.team_benew.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivitySignupBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SignUpActivity: AppCompatActivity(), SignUpView {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterRegister.setOnClickListener{
            signUp()
            onSignUpSuccess()
        }
     }

//    private fun getRegisterUser() : RegisterUser {
//        val id : String = binding.tetSignupId.text.toString()
//        val pwd : String = binding.tetSignupPw.text.toString()
//        val name : String = binding.tetSignupName.text.toString()
//        return RegisterUser(id, pwd, name)
//    }

    private fun signUp() {
        if (binding.tetSignupId.text.toString().isEmpty()) {
            AlertDialog.Builder(this)
                .setMessage("아이디가 비어있습니다")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetSignupId.requestFocus()
                }
                .show()
            return
        }

        if (binding.tetSignupPw.text.toString() != binding.tetSignupPwRecheck.text.toString()) {
            AlertDialog.Builder(this)
                .setMessage("비밀번호가 일치하지 않아요! 재확인해주세요")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetSignupPwRecheck.requestFocus()
                }
                .show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)

        //authService.signUp(getRegisterUser())
    }

    override fun onSignUpSuccess() {

        Log.d("SIGNUP/SUCCESS","회원가입완료")
       finish()
    }

    override fun onSignUpFailure() {
        Log.d("SIGNUP/FAILURE","회원가입오류")
    }


}