package com.gmlab.team_benew.auth.register

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.gmlab.team_benew.databinding.FragmentRegister2Binding

class Register2Fragment : Fragment() {

    private lateinit var binding: FragmentRegister2Binding

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegister2Binding.inflate(inflater, container, false)
        val view = binding.root

        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)

        //000-0000-0000자동 수정
        binding.tetRigister2Phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.btnRegister2Next.setOnClickListener {
            nextClickEvent()
        }

        return view
    }

    private fun nextClickEvent(){
        if (binding.tetRigister2Email.text.toString().isEmpty()) {
            AlertDialog.Builder(requireContext())
                .setMessage("이메일이 비어있습니다")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetRigister2Email.requestFocus()
                }
                .show()
            return
        }

        if (binding.tetRigister2Phone.text.toString().isEmpty()) {
            AlertDialog.Builder(requireContext())
                .setMessage("휴대폰번호가 비어있습니다")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetRigister2Phone.requestFocus()
                }
                .show()
            return
        }

        registerViewModel.r_email = binding.tetRigister2Email.text.toString()
        registerViewModel.r_phoneNumber = binding.tetRigister2Phone.text.toString()

        (requireActivity() as? RegisterActivity)?.viewPager?.let { viewPager ->
            val currentItem = viewPager.currentItem
            if (currentItem < viewPager.adapter?.itemCount ?: 0 - 1) {
                viewPager.setCurrentItem(currentItem + 1, true)
            }
        }
    }
}