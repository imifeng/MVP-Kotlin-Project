package com.kotlin.project.ui.activity

import android.view.View
import com.kotlin.project.R
import com.kotlin.project.base.BaseMvpActivity
import com.kotlin.project.mvp.contract.ReposContract
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.mvp.presenter.ReposPresenter
import com.kotlin.project.utils.DisplayCutoutUtils
import com.kotlin.project.utils.GsonUtils
import com.kotlin.project.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : BaseMvpActivity<ReposContract.View, ReposPresenter>(), ReposContract.View {

    override fun createPresenter() = ReposPresenter()

    override val layoutId: Int = R.layout.activity_repos

    override fun initView() {
        DisplayCutoutUtils.adaptStatusBarHeight(this, status_bar)
    }

    override fun initData() {
    }

    override fun initEvent() {
        setOnClickListener(iv_back, View.OnClickListener { finish() })

        setOnClickListener(tv_repos_btn, View.OnClickListener {
            if (et_username.text.toString().trim().isNullOrEmpty()){
                ToastUtils.showToast(getString(R.string.repos_text_hint_username))
                return@OnClickListener
            }
            getPresenter()?.getRepos(et_username.text.toString().trim())

        })
    }

    override fun onBack() {
        finish()
    }

    override fun onReposSuccess(response: List<RepoBean>) {
        ToastUtils.showToast("getRepos Successful:" + ":" + GsonUtils.get().toJson(response))
    }
}