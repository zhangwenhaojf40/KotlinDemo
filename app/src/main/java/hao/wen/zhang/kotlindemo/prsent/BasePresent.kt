package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.base.IView
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 4:38
 * 描述说明：
 */
abstract class BasePresent<T: IView> {


    var disposable: CompositeDisposable? = null
    var mView: T? = null
    fun attachView(mView: T) {
        this.mView = mView
        disposable = CompositeDisposable()
    }

    fun detachView() {
        unDisposable()//切断流，防止rx内存泄漏，导致空指针
        if (mView != null)
            mView = null
    }

    private fun unDisposable() {
        if (disposable != null) {

            disposable!!.clear()
            disposable = null
        }


    }

    fun addDisposable(dis: Disposable) {
        if (disposable != null) {
            disposable!!.add(dis)
        }
    }

    fun <T> Loading(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .doOnSubscribe{ mView?.showLoading() }
                    .doOnError { mView!!.error() }
                    .doOnComplete{mView!!.dissError()}
                    .doFinally { mView?.dissmassLoading() }
        }
    }
}