package com.kotlin.project.ui.repos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.project.R
import com.kotlin.project.base.BaseMvpActivity
import com.kotlin.project.extension.gone
import com.kotlin.project.extension.listener.setOnEventClickListener
import com.kotlin.project.extension.visible
import com.kotlin.project.mvp.contract.ReposContract
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.mvp.presenter.ReposPresenter
import com.kotlin.project.ui.repos.adapter.ReposAdapter
import com.kotlin.project.utils.NetworkConnectedListener
import com.kotlin.project.utils.NetworkManger
import com.kotlin.project.utils.ToastUtils
import com.kotlin.project.utils.adaptStatusBarHeight
import kotlinx.android.synthetic.main.activity_repos.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP) // API > 21
class ReposActivity : BaseMvpActivity<ReposContract.View, ReposPresenter>(), ReposContract.View,
    NetworkConnectedListener {

    override fun createPresenter() = ReposPresenter()

    override val layoutId: Int = R.layout.activity_repos

    private val layoutManager by lazy { LinearLayoutManager(this) }
    private val reposAdapter by lazy { ReposAdapter() }

    override fun initView() {
        status_bar.adaptStatusBarHeight()
        // 注册网络监听
        NetworkManger.getInstance().registerNetworkCallback(this)

        rv_repos.layoutManager = layoutManager
        rv_repos.adapter = reposAdapter
        // 适配器回调：显示是否有数据
        reposAdapter.onNoProducts = {
            if (it) {
                tv_no_data.visible()
            } else {
                tv_no_data.gone()
            }
        }
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

    override fun onReposSuccess(response: List<RepoBean>) {
        reposAdapter.setData(response)
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

    override fun handleBack(): Boolean {
        // 屏蔽返回按钮
        ToastUtils.showToast("已经屏蔽了返回按钮")
        return true
    }
}