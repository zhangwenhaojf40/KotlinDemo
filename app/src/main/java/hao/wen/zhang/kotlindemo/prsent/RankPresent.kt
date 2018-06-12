package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.http.BaseObser
import hao.wen.zhang.kotlindemo.http.RetrofitManager
import hao.wen.zhang.kotlindemo.module.RankBean
import hao.wen.zhang.kotlindemo.util.RxSchedulers
import hao.wen.zhang.kotlindemo.view.i.IRankView
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   下午 2:39
 * 描述说明：
 */
class RankPresent :BasePresent<IRankView>(),INetPresent {
    var urls:String=""
    override fun request() {
        RetrofitManager
                .newInstans()
                .net()
                .getRankClassifyData(urls)
                .compose(RxSchedulers.io_main())
                .compose(Loading())
                .subscribe(object :BaseObser<RankBean>(){
                    override fun onSubscribe(d: Disposable) {
                        disposable?.add(d)
                    }

                    override fun onNext(t: RankBean) {
                        mView?.getData(t)
                    }
                })
    }

    fun setUrl(urls: String) {
        this.urls=urls
    }
}