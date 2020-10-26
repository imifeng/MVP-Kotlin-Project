package com.kotlin.project.ui.test

import android.os.Bundle
import com.kotlin.project.R
import com.kotlin.project.base.BaseActivity
import com.kotlin.project.base.BaseFragment
import com.kotlin.project.common.Constant
import com.kotlin.project.ui.test.fragment.TestReposFragment
import com.kotlin.project.extension.listener.setOnEventClickListener
import com.kotlin.project.utils.ToastUtils
import com.kotlin.project.utils.adaptStatusBarHeight
import kotlinx.android.synthetic.main.activity_fragment_test.*

class TestFragmentActivity : BaseActivity() {

    override val layoutId = R.layout.activity_fragment_test

    override fun initView() {
        super.initView()

        //适配刘海高度
        status_bar.adaptStatusBarHeight()

        val bundle = Bundle()
        bundle.putString(Constant.EXTRA_ACTION_TEST, "Test")
        val testReposFragment = TestReposFragment()
        testReposFragment.arguments = bundle
        openFragment(testReposFragment)

    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()
        //使用了有点击效果
        iv_back.setOnEventClickListener { finish() }
    }

    private fun openFragment(fragment: BaseFragment) {
        ToastUtils.showToast(fragment::class.simpleName)
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.test_fragment, fragment)
            .addToBackStack(fragment::class.simpleName).commitAllowingStateLoss()
    }
}