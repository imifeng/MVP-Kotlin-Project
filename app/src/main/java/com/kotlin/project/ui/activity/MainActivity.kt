package com.kotlin.project.ui.activity

import android.content.Intent
import android.view.View
import com.kotlin.project.R
import com.kotlin.project.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {

        setOnClickListener(btn_menu, View.OnClickListener {
            //goto Test
            val intent = Intent(this, MenuTestActivity::class.java)
            startActivity(intent)
        })

        setOnClickListener(btn_test, View.OnClickListener {
            //goto Test
            val intent = Intent(this, TestFragmentActivity::class.java)
            startActivity(intent)
        })

        setOnClickListener(btn_repos, View.OnClickListener {
            val intent = Intent(this, ReposActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onBack() {
        finish()
    }

}
