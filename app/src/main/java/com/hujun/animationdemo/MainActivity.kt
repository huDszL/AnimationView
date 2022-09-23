package com.hujun.animationdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var anim  = AnimationUtils.loadAnimation(this, R.anim.demo1)

        // 不能放在点击事件中， 初始化需要在oncreate中实现
        val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ map ->
            if( map.size > 0 &&
                map.get(Manifest.permission.CALL_PHONE) == true
            ){
                call()
            }
        }
        anim_tv.setOnClickListener {
//            anim_tv.startAnimation(anim)
//            animationView(it)
//            requestPermissions(this,arrayOf(Manifest.permission.CALL_PHONE),1)
//            launcher.launch(arrayOf(Manifest.permission.CALL_PHONE))  权限的测试
        }
//        anim_tv.startAnimation(anim)
//        requ

    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(requestCode == 1){
//            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                call()
//            }
//        }
//    }

    /**
     *  startAnimation方法是立刻播放动画；setAnimation是设置要播放的下一个动画。
     *  setAnimationListener可以监听动画的开始、结束、重复。
     */
    fun animationView(v: View){
        val animation = AnimationSet(false).apply {
            duration = 3000
            addAnimation(TranslateAnimation(0F, 100F, 0F, 0F))
            addAnimation(ScaleAnimation(0.1f,1f,0.1f,1f))
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Toast.makeText(this@MainActivity , "animation is over",Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
        }
        v.startAnimation(animation)
    }

    fun call(){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)

    }


}