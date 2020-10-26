package com.kotlin.project.ui.test

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.BounceInterpolator
import android.widget.TextView
import com.kotlin.project.R
import com.kotlin.project.base.BaseActivity
import com.kotlin.project.extension.gone
import com.kotlin.project.extension.visible
import com.kotlin.project.extension.listener.setOnEventClickListener
import kotlinx.android.synthetic.main.activity_menu_test.*


class MenuTestActivity : BaseActivity() {

    private var isMenuOpen = false

    private var textViews = ArrayList<TextView>()

    override val layoutId: Int = R.layout.activity_menu_test

    override fun initView() {
        textViews.add(tv_1)
        textViews.add(tv_2)
        textViews.add(tv_3)
        textViews.add(tv_4)

    }

    override fun initEvent() {
        img_publish.setOnEventClickListener {
            if (!isMenuOpen) {
                showOpenAnim(180)
                img_publish.setImageResource(R.drawable.ic_close)
                img_publish.setBackgroundResource(R.drawable.bg_circle_button_primary_purple)
            } else {
                showCloseAnim(180)
                img_publish.setImageResource(R.drawable.ic_up_down_arrows_b)
                img_publish.setBackgroundResource(R.drawable.bg_circle_button_primary_white)

            }
        }
    }


    private fun showOpenAnim(dp: Int) {
        img_publish.isEnabled = false

        //for循环来开始小图标的出现动画
        for (i in textViews.indices) {
            textViews[i].visible()
            val set = AnimatorSet()
            val x = getAnimatorSetX(dp, i)
            val y = getAnimatorSetY(dp, i)
            set.playTogether(
                ObjectAnimator.ofFloat(
                    textViews[i],
                    "translationX",
                    (x * 0.25).toFloat(),
                    x.toFloat()
                ),
                ObjectAnimator.ofFloat(
                    textViews[i],
                    "translationY",
                    (y * 0.25).toFloat(),
                    y.toFloat()
                )
                , ObjectAnimator.ofFloat(textViews[i], "alpha", 0f, 1f).setDuration(1000)
            )
            set.interpolator = BounceInterpolator()
            set.setDuration(500).startDelay = 100
            set.start()
            set.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    //菜单状态置开启
                    isMenuOpen = true
                    img_publish.isEnabled = true
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }

        //转动加号大图标本身45°
        val rotate: ObjectAnimator =
            ObjectAnimator.ofFloat(img_publish, "rotation", 0F, 90F).setDuration(300)
        rotate.interpolator = BounceInterpolator()
        rotate.start()
    }

    private fun showCloseAnim(dp: Int) {
        img_publish.isEnabled = false
        //for循环来开始小图标的出现动画
        for (i in textViews.indices) {
            val set = AnimatorSet()
            val x: Double = getAnimatorSetX(dp, i)
            val y: Double = getAnimatorSetY(dp, i)
            set.playTogether(
                ObjectAnimator.ofFloat(
                    textViews[i],
                    "translationX",
                    x.toFloat(),
                    (x * 0.25).toFloat()
                ),
                ObjectAnimator.ofFloat(
                    textViews[i],
                    "translationY",
                    y.toFloat(),
                    (y * 0.25).toFloat()
                )
                , ObjectAnimator.ofFloat(textViews[i], "alpha", 1f, 0f).setDuration(800)
            )
            set.interpolator = BounceInterpolator()
            set.setDuration(500).startDelay = 30
            set.start()
            set.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    textViews[i].gone()
                    //菜单状态置关闭
                    isMenuOpen = false
                    img_publish.isEnabled = true
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }

        //转动加号大图标本身45°
        val rotate: ObjectAnimator =
            ObjectAnimator.ofFloat(img_publish, "rotation", 0F, 90F).setDuration(300)
        rotate.interpolator = BounceInterpolator()
        rotate.start()
    }

    private fun getAnimatorSetX(dp: Int, position: Int): Double {
        when (position) {
            0 -> {
                val a = -Math.cos(30 * Math.PI / 180)
                return a * dip2px(dp)
            }
            1->{
                val a = -Math.cos(30 * Math.PI / 180)
                return a * dip2px(dp)
            }
            2->{
                return 0.0
            }
            3->{
                return 0.0
            }
            else ->{
                return 0.0
            }
        }
    }

    private fun getAnimatorSetY(dp: Int, position: Int): Double {
        when (position) {
            0 -> {
                val b = -Math.sin(30 * Math.PI / 180)
                return b * dip2px(dp)
            }
            1->{
                val b = Math.sin(30 * Math.PI / 180)
                return b * dip2px(dp)
            }
            2->{
                val b = -Math.sin(90 * Math.PI / 180)
                return b * dip2px(dp)
            }
            3->{
                val b = Math.sin(90 * Math.PI / 180)
                return b * dip2px(dp)
            }
            else ->{
                return 0.0
            }
        }
    }

    private fun dip2px(value: Int): Int {
        val density = resources
            .displayMetrics.density
        return (density * value + 0.5f).toInt()
    }


    override fun onBack() {
        finish()
    }

}
