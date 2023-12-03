package com.gmlab.team_benew.auth.register

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.gmlab.team_benew.R
import com.gmlab.team_benew.auth.AuthService
import com.gmlab.team_benew.auth.RegisterUser
import com.gmlab.team_benew.auth.SignUpView
import com.gmlab.team_benew.databinding.FragmentRegister3Binding
import java.util.Calendar

class Register3Fragment : Fragment(), SignUpView {

    private lateinit var binding: FragmentRegister3Binding

    lateinit var registerViewModel: RegisterViewModel

    var gender : String = ""
    var major : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegister3Binding.inflate(inflater, container, false)
        val view = binding.root

        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)

        binding.btnRegister3Birthday.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnRegister3Register.setOnClickListener {
            registerEvent()
        }

        binding.radiogroupRegister3Gender.setOnCheckedChangeListener { group, checkedId ->
            gender = when (checkedId) {
                R.id.radioButton_register3_Male -> "남성"
                R.id.radioButton_register3_Female -> "여성"
                else -> ""
            }
        }

        binding.radiogroupRegister3Major.setOnCheckedChangeListener{ group, checkedId ->
            major = when(checkedId){
                R.id.radioButton_register3_ture -> "전공자"
                R.id.radioButton_register3_false -> "비전공자"
                else -> ""
            }
        }

        return view
    }

    private fun getRegisterUser() : RegisterUser {
        val id : String = registerViewModel.r_account
        val pwd : String = registerViewModel.r_password
        val name : String = registerViewModel.r_name
        val email : String = registerViewModel.r_email
        val phoneNumber : String = registerViewModel.r_phoneNumber
        val birthday : String = binding.btnRegister3Birthday.text.toString()


        return RegisterUser(id, pwd, name, gender, birthday, email, major, phoneNumber)
    }

    private fun registerEvent(){
        if (binding.btnRegister3Birthday.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "생년월일이 비어있습니다.", Toast.LENGTH_SHORT).show();
            return
        }

        if(binding.radiogroupRegister3Gender.checkedRadioButtonId == -1)
        {
            Toast.makeText(requireContext(), "성별을 선택하여 주세요", Toast.LENGTH_SHORT).show();
            return
        }

        if(binding.radiogroupRegister3Major.checkedRadioButtonId == -1)
        {
            Toast.makeText(requireContext(), "전공여부를 선택하여 주세요", Toast.LENGTH_SHORT).show();
            return
        }

        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getRegisterUser())
    }

    private fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()

        calendar.set(2000, 1, 1)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                val selectedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDayOfMonth)
                binding.btnRegister3Birthday.text = selectedDate
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    override fun onSignUpSuccess() {
        activity?.finish()
    }

    override fun onSignUpFailure() {
    }

}