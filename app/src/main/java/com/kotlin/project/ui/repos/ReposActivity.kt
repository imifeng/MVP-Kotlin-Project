package com.kotlin.project.ui.repos

import android.os.Build
import androidx.annotation.RequiresApi
import com.kotlin.project.R
import com.kotlin.project.base.BaseMvpActivity
import com.kotlin.project.mvp.contract.ReposContract
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.mvp.presenter.ReposPresenter
import com.kotlin.project.extension.listener.setOnEventClickListener
import com.kotlin.project.utils.*
import kotlinx.android.synthetic.main.activity_repos.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP) // API > 21
class ReposActivity : BaseMvpActivity<ReposContract.View, ReposPresenter>(), ReposContract.View,
    NetworkConnectedListener {

    override fun createPresenter() = ReposPresenter()

    override val layoutId: Int = R.layout.activity_repos

    override fun initView() {
        status_bar.adaptStatusBarHeight()
        //注册网络监听
        NetworkManger.getInstance().registerNetworkCallback(this)
    }

    override fun initData() {
    }

    override fun initEvent() {
        iv_back.setOnEventClickListener {
            finish()
        }

        tv_repos_btn.setOnEventClickListener {
            if (et_username.text.toString().trim().isNullOrEmpty()) {
                ToastUtils.showToast(getString(R.string.repos_text_hint_username))
                return@setOnEventClickListener
            }
            getPresenter()?.getRepos(et_username.text.toString().trim())
        }
    }

    override fun onBack() {
        finish()
    }

    override fun onReposSuccess(response: List<RepoBean>) {
        ToastUtils.showToast("getRepos Successful:" + ":" + GsonUtils.get().toJson(response))
    }

    /**
     * 网络监听回调
     */
    override fun networkConnected(isConnected: Boolean) {
        if (isConnected) {
            //有网络
            ToastUtils.showToast("网络连接上了")
        } else {
            //无网络
            ToastUtils.showToast("失去网络了")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //注销网络监听
        NetworkManger.getInstance().unregisterNetworkCallback()
    }
}