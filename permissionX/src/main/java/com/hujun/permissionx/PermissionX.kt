package com.hujun.permissionx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object PermissionX  {

    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity , vararg permissions:String , callback: ((Boolean, List<String>) -> Unit) ){
        val mananger = activity.supportFragmentManager
        val findFragmentByTag = mananger.findFragmentByTag(TAG)
        val fragment = if(findFragmentByTag != null){
            findFragmentByTag as InvisibleFrament
        }else{
            val invisibleFrament = InvisibleFrament()
            mananger.beginTransaction().add(invisibleFrament, TAG).commitNow();
            invisibleFrament
        }
        fragment.requestNow(callback,*permissions)
    }
}