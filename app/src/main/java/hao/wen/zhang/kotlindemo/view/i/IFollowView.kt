package hao.wen.zhang.kotlindemo.view.i

import hao.wen.zhang.kotlindemo.base.IBaseView

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 3:54
 * 描述说明：\
 */
interface IFollowView<T> :IBaseView<T> {
    /*
    * 设置适配器
    * */
    fun setAdapeter()
    /*
    * 联网
    * */
    fun setNet()
    /*
    * 加载更多
    * */
    fun loadMore()

}