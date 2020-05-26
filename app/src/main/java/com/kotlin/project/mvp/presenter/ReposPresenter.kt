package com.kotlin.project.mvp.presenter

import com.kotlin.project.base.BasePresenter
import com.kotlin.project.mvp.contract.ReposContract
import com.kotlin.project.mvp.model.ReposModel
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.net.NetObserver
import com.kotlin.project.net.NetRequestListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * @author Finn
 * @date 2020
 */
open class ReposPresenter : BasePresenter<ReposContract.View>(), ReposContract.Presenter {

    private val model: ReposContract.Model by lazy {
        ReposModel()
    }

    override fun getRepos(username: String) {
        pView?.showLoading()
        requestNetwork(
            model.getRepos(username),
            object : NetRequestListener<List<RepoBean>>{
                override fun onSuccess(response: List<RepoBean>) {
                    pView?.onReposSuccess(response)
                }

                override fun onFailed(e: Throwable?) {
                    pView?.onError(e)
                }

            })
    }
}