package hao.wen.zhang.kotlindemo.view.i

import hao.wen.zhang.kotlindemo.base.IBaseView

/**
 * 作者：ZWH
 * 创建日期： 2018/6/12 0012   下午 1:31
 * 描述说明：
 */
interface ICategoryView<T> :IBaseView<T> {
    /*
    * 设置适配器
    * */
    fun setAdapter()

    /*
    * 联网
    * */
    fun setNet()

}