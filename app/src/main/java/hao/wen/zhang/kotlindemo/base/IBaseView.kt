package hao.wen.zhang.kotlindemo.base

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   上午 10:43
 * 描述说明：
 */
interface IBaseView<T> :IView{
    fun getData(data:T)
}