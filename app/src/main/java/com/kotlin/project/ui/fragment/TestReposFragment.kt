package com.kotlin.project.ui.fragment

import android.view.View
import com.kotlin.project.R
import com.kotlin.project.base.BaseMvpFragment
import com.kotlin.project.common.Constant
import com.kotlin.project.mvp.contract.TestContract
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.mvp.presenter.TestPresenter
import com.kotlin.project.utils.GsonUtils
import com.kotlin.project.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_test_repos.*

class TestReposFragment : BaseMvpFragment<TestContract.View, TestPresenter>(), TestContract.View {


    override fun createPresenter() = TestPresenter()

    override fun getLayoutId() = R.layout.fragment_test_repos

    override fun initView(view: View?) {
        super.initView(view)
        val testStr = arguments?.getString(Constant.EXTRA_ACTION_TEST)
        //Test arguments
        ToastUtils.showToast(testStr)
    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()

        setOnClickListener(tv_repos_btn, View.OnClickListener {
            if (et_username.text.toString().trim().isNullOrEmpty()) {
                ToastUtils.showToast(getString(R.string.repos_text_hint_username))
                return@OnClickListener
            }
            getPresenter()?.getRepos(et_username.text.toString().trim())
        })
    }


    override fun onReposSuccess(response: List<RepoBean>) {
        ToastUtils.showToast("getRepos Successful:" + ":" + GsonUtils.get().toJson(response))
    }
}