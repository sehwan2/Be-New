package com.gmlab.team_benew.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmlab.team_benew.R

class MatchingIntroFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intro_matching, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonNavMatchingDetail = view.findViewById<CardView>(R.id.cv_coworker_searching_intro_container)

        // 모든 버튼에 같은 클릭 리스너 설정
        buttonNavMatchingDetail.setOnClickListener { onCardClicked(it) }
    }

    private fun onCardClicked(view: View) {
        when (view.id) {
            R.id.cv_coworker_searching_intro_container -> findNavController().navigate(R.id.action_intro_matching_to_navigation_matching) // 매칭 디테일로 이동
        }
    }
}