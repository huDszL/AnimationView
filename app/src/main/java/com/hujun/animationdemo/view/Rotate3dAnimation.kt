package com.hujun.animationdemo.view

import android.graphics.Camera
import android.view.animation.Animation

class Rotate3dAnimation(val mFromDegrees :Float ) : Animation() {


    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)

    }
}