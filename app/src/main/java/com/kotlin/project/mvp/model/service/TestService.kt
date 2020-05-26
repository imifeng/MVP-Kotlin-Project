package com.kotlin.project.mvp.model.service

import com.kotlin.project.mvp.model.bean.RepoBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Finn
 * @date 2020
 */
interface TestService {
    /**
     * 网络请求方法:@GET、@POST、@PUT、@DELETE、@HEAD(常用)
     * 网络请求标记: @FormUrlEncoded、@Multipart、@Streaming
     * 网络请求参数: @Header &、@Headers、 @Body、@Field 、 @FieldMap、@Part 、 @PartMap、@Query、@QueryMap、@Path、@Url
     */
    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String?): Observable<List<RepoBean>>
}