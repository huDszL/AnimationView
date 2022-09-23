package com.hujun.permissionx

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class InvisibleFrament : Fragment() {

    // 定义一个函数类型变量
    private var callback: ((Boolean, List<String>) -> Unit) ?= null

    /**
     * * {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing
     * in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and
     * handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.
     */
    fun requestNow( cb :((Boolean, List<String>) -> Unit) , vararg permissions:String){
        callback = cb
        requestPermissions(permissions, 1)
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            val deniedList = ArrayList<String>()
            for((index, result ) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val callGranted = deniedList.isEmpty()
            callback?.let {
                it(callGranted, deniedList)
            }
        }


    }

    fun getType(){

    }
}