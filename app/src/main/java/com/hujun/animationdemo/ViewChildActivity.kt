package com.hujun.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import kotlinx.android.synthetic.main.activity_view_child.*

class ViewChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_child)

        layoutAnimation() ;
    }


    // 代码的方式 实现Layoutanimation
    fun layoutAnimation(){
        var animation = AnimationUtils.loadAnimation(this,R.anim.enter_from_left_for_child_of_group)
        var controll = LayoutAnimationController(animation)
        controll.delay=0.8f
        controll.order = LayoutAnimationController.ORDER_NORMAL
        top_layout.layoutAnimation = controll
    }
}