package com.kotlin.project.mvp.contract

import com.kotlin.project.base.BaseView
import com.kotlin.project.mvp.model.bean.RepoBean
import io.reactivex.rxjava3.core.Observable

/**
 * @author Finn
 * @date 2020
 */
interface TestContract {
    interface Model {
        fun getRepos(username: String): Observable<List<RepoBean>>
    }

    interface View : BaseView {
        fun onReposSuccess(response: List<RepoBean>)
    }

    interface Presenter {
        /**
         * get Repos
         *
         * @param username
         */
        fun getRepos(username: String)
    }
}