package com.gmlab.team_benew.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivitySignupBinding
import com.gmlab.team_benew.databinding.FragmentRegister1Binding

class Register1Fragment : Fragment() {

    private lateinit var binding: FragmentRegister1Binding

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegister1Binding.inflate(inflater, container, false)
        val view = binding.root

        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)

        binding.btnRegister1Next.setOnClickListener {
            nextClickEvent()
        }

        return view
    }

    private fun nextClickEvent(){
        if (binding.tetRigister1Id.text.toString().isEmpty()) {
            AlertDialog.Builder(requireContext())
                .setMessage("아이디가 비어있습니다")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetRigister1Id.requestFocus()
                }
                .show()
            return
        }

        if (binding.tetRigister1Pw.text.toString() != binding.tetRigister1PwRecheck.text.toString()) {
            AlertDialog.Builder(requireContext())
                .setMessage("비밀번호가 일치하지 않아요! 재확인해주세요")
                .setPositiveButton("확인") { dialog, which ->
                    binding.tetRigister1PwRecheck.requestFocus()
                }
                .show()
            return
        }

        registerViewModel.r_account = binding.tetRigister1Id.text.toString()
        registerViewModel.r_password = binding.tetRigister1Pw.text.toString()
        registerViewModel.r_name = binding.tetRigister1Name.text.toString()

        (requireActivity() as? RegisterActivity)?.viewPager?.let { viewPager ->
            val currentItem = viewPager.currentItem
            if (currentItem < viewPager.adapter?.itemCount ?: 0 - 1) {
                viewPager.setCurrentItem(currentItem + 1, true)
            }
        }
    }

}