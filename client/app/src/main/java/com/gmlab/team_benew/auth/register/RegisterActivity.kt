package com.gmlab.team_benew.auth.register

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gmlab.team_benew.R

class RegisterActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2

    lateinit var registerViewModel: RegisterViewModel

    //온보딩 프래그먼트
    private val registerFragments: List<Fragment> = listOf(
        Register1Fragment(),
        Register2Fragment(),
        Register3Fragment()
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        viewPager = findViewById(R.id.vp_rigister_view)

        viewPager.adapter = OnboardingPagerAdapter(this@RegisterActivity)

        viewPager.isUserInputEnabled = false
    }

    inner class OnboardingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return registerFragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return registerFragments[position]
        }
    }

    override fun onBackPressed() {
        val viewPager = findViewById<ViewPager2>(R.id.vp_rigister_view)

        val currentItem = viewPager.currentItem

        if (currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.setCurrentItem(currentItem - 1, true)
        }
    }

}