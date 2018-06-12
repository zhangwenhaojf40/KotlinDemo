package hao.wen.zhang.kotlindemo.util

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   上午 10:14
 * 描述说明：
 */
object RxSchedulers {

    fun <T> io_main(): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())//指定联网请求的线程，事件产生的线程
                    .observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程

        }
    }


}