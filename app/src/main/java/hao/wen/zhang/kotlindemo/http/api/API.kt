package xifuyin.tumour.com.a51ehealth.kotlin_simple.net.api

import hao.wen.zhang.kotlindemo.module.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


/**
 * Created by Administrator on 2018/5/22.
 */
interface API {

    /**
     * 首页精选
     */
    @GET
    fun getFirstHomeData(@Url url:String ): Observable<HomeBean>

    /**
     * 获取全部排行榜的Info（包括，title 和 Url）
     */
    @GET("v4/rankList")
    fun getRankList(): Observable<TabInfoBean>


    /**
     * 获取分类排行
     */
    @GET
    fun getRankClassifyData(@Url url: String): Observable<RankBean>

    @GET("v4/tabs/follow")
    fun getFollowData():Observable<FollowBean>

    /**
     * 获取更多的关注
     */
    @GET
    fun getFollowData(@Url url: String): Observable<FollowBean>
    /**
     * 获取分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

}

