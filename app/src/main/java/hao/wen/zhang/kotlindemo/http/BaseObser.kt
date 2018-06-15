package hao.wen.zhang.kotlindemo.http

import io.reactivex.Observer


/**
 * 作者：ZWH
 * 创建日期： 2018/6/6 0006   上午 11:56
 * 描述说明：
 */

abstract class BaseObser<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {

    }

    abstract override fun onNext(t: T)





}
