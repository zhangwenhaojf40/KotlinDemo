package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.http.BaseObser
import hao.wen.zhang.kotlindemo.http.RetrofitManager
import hao.wen.zhang.kotlindemo.module.FollowBean
import hao.wen.zhang.kotlindemo.util.RxSchedulers
import hao.wen.zhang.kotlindemo.view.i.IFollowView
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 3:54
 * 描述说明：
 */
class FollowPresent :BasePresent<IFollowView<FollowBean>>(),INetPresent {
    var urls="http://baobab.kaiyanapp.com/api/v4/tabs/follow"
    override fun request() {
        RetrofitManager.newInstans()
                .net()
                .getFollowData(urls)
                .compose(RxSchedulers.io_main())
                .compose(Loading())
                .subscribe(object :BaseObser<FollowBean>(){
                    override fun onSubscribe(d: Disposable) {
                        disposable?.add(d)
                    }

                    override fun onNext(t: FollowBean) {
                        urls=t.nextPageUrl
                        mView?.getData(t)
                    }

                })
    }


}