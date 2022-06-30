package com.example.punchapp.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.punchapp.ProductFragment
import com.example.punchapp.PurchaseHistoryFragment
import com.example.punchapp.RedeemFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :  FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {ProductFragment()}
            1 -> {RedeemFragment()}
            2 -> {PurchaseHistoryFragment()}
            else -> {throw Resources.NotFoundException("PositionNotFound")}
        }
    }


}