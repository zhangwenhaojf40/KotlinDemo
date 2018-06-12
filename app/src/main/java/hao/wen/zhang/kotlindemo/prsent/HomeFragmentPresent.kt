package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.http.BaseObser
import hao.wen.zhang.kotlindemo.http.RetrofitManager
import hao.wen.zhang.kotlindemo.module.HomeBean
import hao.wen.zhang.kotlindemo.util.RxSchedulers
import hao.wen.zhang.kotlindemo.view.i.IHomeFragment
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   下午 4:52
 * 描述说明：
 */

class HomeFragmentPresent : BasePresent<IHomeFragment<HomeBean>>(),INetPresent {
    var url: String=  "http://baobab.kaiyanapp.com/api/v2/feed?date=1528074000000&num=1"
    override fun request() {
        RetrofitManager.newInstans().net()
        .getFirstHomeData(url)
                .compose (RxSchedulers.io_main())

                .compose(Loading())
                .subscribe(object : BaseObser<HomeBean>(){
                    override fun onSubscribe(d: Disposable) {
                        disposable!!.add(d)//加入统一管理
                    }

                    override fun onNext(homeBean: HomeBean) {
                        val bannerItemList = homeBean.issueList[0].itemList
                        url=homeBean.nextPageUrl
                        bannerItemList.filter { t->t.type == "textHeader" }.forEach({d->bannerItemList.remove(d)})
                        mView!!.getData(homeBean)
                    }

                })

}


}