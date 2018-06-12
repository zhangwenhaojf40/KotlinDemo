package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.http.BaseObser
import hao.wen.zhang.kotlindemo.http.RetrofitManager
import hao.wen.zhang.kotlindemo.module.TabInfoBean
import hao.wen.zhang.kotlindemo.util.RxSchedulers
import hao.wen.zhang.kotlindemo.view.i.IHotView
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   上午 10:27
 * 描述说明：
 */
class HotPresent : BasePresent<IHotView<TabInfoBean>>(),INetPresent {
    override fun request() {
        RetrofitManager.newInstans()
                .net()
                .getRankList()
                .compose (RxSchedulers.io_main())
                .compose(Loading())
                .subscribe(object : BaseObser<TabInfoBean>() {
                    override fun onNext(t: TabInfoBean) {
                        mView!!.getData(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable!!.add(d)
                    }
                })

    }

}