package hao.wen.zhang.kotlindemo.prsent

import hao.wen.zhang.kotlindemo.http.BaseObser
import hao.wen.zhang.kotlindemo.http.RetrofitManager
import hao.wen.zhang.kotlindemo.module.CategoryBean
import hao.wen.zhang.kotlindemo.util.RxSchedulers
import hao.wen.zhang.kotlindemo.view.i.ICategoryView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 作者：ZWH
 * 创建日期： 2018/6/12 0012   下午 1:30
 * 描述说明：
 */
class CategoryPresent : BasePresent<ICategoryView<ArrayList<CategoryBean>>>(),INetPresent {
    override fun request() {

        RetrofitManager
                .newInstans()
                .net()
                .getCategory()
                .compose(RxSchedulers.io_main())
                .compose(Loading())
                .subscribe(object : BaseObser<ArrayList<CategoryBean>>() {
                    override fun onNext(t: ArrayList<CategoryBean>) {
                        mView?.getData(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable?.add(d)
                    }


                })
    }

}