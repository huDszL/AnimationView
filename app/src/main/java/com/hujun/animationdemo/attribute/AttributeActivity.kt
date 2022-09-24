package com.hujun.animationdemo.attribute

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.core.animation.addPauseListener
import com.hujun.animationdemo.R
import kotlinx.android.synthetic.main.activity_attribute.*

/**
 * 属性动画可对任意对象做动画，不仅仅是View。默认动画时间是300ms，10ms/帧。具体理解就是：可在给定的时间间隔内 实现 对象的某属性值 从 value1 到 value2的改变。
 * 使用很简单，可以直接代码实现（推荐），也可xml实现，举例如下：
 */
class AttributeActivity : AppCompatActivity() {

//    lateinit var attrV : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attribute)
//        attrV = attr_tv
        attributeAnimation()
    }


    fun attributeAnimation(){
        // 属性动画，方式一,代码:建议使用 , 横移
        val objAnim = ObjectAnimator.ofFloat(attr_tv,"translationXY",0f,200f) // 第一个参数：目标view ,二：动画类型，三：开始值，四：结束值
        objAnim.duration = 1000
        objAnim.interpolator = LinearInterpolator()
        setAnimationListener(objAnim)

        var mAnimatorSet = AnimatorSet().apply {
            play(objAnim)
//            with()
//            after()
        }

        mAnimatorSet.start()
    }


    fun setAnimationListener(translationX : ObjectAnimator){
        translationX.addUpdateListener {
            // 没播放一帧都会调用
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            translationX.addPauseListener {
                //
            }
        }
        translationX.addListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
            }
        })
    }

    /**
     * 注意点：
     * 关于View动画和属性动画的平移，属性动画改变属性值setTranslationX 的视图效果像view动画的平移一样，都是view实际的layout位置没变，只改变了视图位置；
     * 不同点是属性动画 给触摸点生效区域增加了位移（而view动画仅改变了视图位置）。
     * 插值器：Interpolator，根据 时间流逝的百分比，计算当前属性值改变的百分比。
     * 例如duration是1000，start后过了200，那么时间百分比是0.2，那么如果差值器是LinearInterpolator线性差值器，那么属性值改变的百分比也是0.2
     * 估值器：Evaluator，就是根据 差值器获取的 属性值百分比，计算改变后的属性值。ofInt、onFloat内部会自动设置IntEvaluator、FloatEvaluator。
     * 如果使用ofInt且是颜色相关的属性，就要设置ArgbEvaluator。上面例子中 文字颜色变化动画 设置了ArgbEvaluator：textColor.setEvaluator(new ArgbEvaluator())。
     * 动画监听：主要是两个监听接口，AnimatorUpdateListener、AnimatorListenerAdapter。AnimatorUpdateListener的回调方法在每帧更新时都会调用一次；
     * AnimatorListenerAdapter可以监听开始、结束、暂停、继续、重复、取消，重写你要关注的方法即可。
     */

}