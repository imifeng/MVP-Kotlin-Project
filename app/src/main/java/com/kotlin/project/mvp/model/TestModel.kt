package com.kotlin.project.mvp.model

import com.kotlin.project.base.BaseModel
import com.kotlin.project.mvp.contract.ReposContract
import com.kotlin.project.mvp.contract.TestContract
import com.kotlin.project.mvp.model.bean.RepoBean
import com.kotlin.project.mvp.model.service.ReposService
import io.reactivex.rxjava3.core.Observable


/**
 * @author Finn
 * @date 2020
 */
class TestModel: BaseModel(), TestContract.Model {

    override fun getRepos(username: String): Observable<List<RepoBean>> {
        return repository.create(ReposService::class.java).getRepos(username)
    }

}