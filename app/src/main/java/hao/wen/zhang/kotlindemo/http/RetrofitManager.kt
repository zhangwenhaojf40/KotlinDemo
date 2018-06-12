package hao.wen.zhang.kotlindemo.http

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xifuyin.tumour.com.a51ehealth.kotlin_simple.net.api.API
import java.util.logging.Level

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 4:53
 * 描述说明：
 */

class RetrofitManager{

    companion object {
        var retrofit:RetrofitManager?=null;

        fun newInstans():RetrofitManager {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    retrofit = RetrofitManager()

                }
            }
            return retrofit!!
        }


    }
    fun net():API  {
        return   Retrofit.Builder()
                .baseUrl(NetUtils.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加响应式编程的适配器
                .client(okClient())
                .build()
                .create(API::class.java)
    }
    var httpLog=HttpLoggingInterceptor({
        msg-> Log.e("http",msg)
    })

    fun okClient():OkHttpClient {
        httpLog.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
                .Builder()
                .addInterceptor(httpLog)
                .build()

    }


}
