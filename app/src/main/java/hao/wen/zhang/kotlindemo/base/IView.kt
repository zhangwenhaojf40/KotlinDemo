package hao.wen.zhang.kotlindemo.base

/**
 * 作者：ZWH
 * 创建日期： 2018/6/6 0006   下午 1:44
 * 描述说明：
 */
interface IView {
    //显示加载提示
    fun showLoading()

    //隐藏加载提示
    fun dissmassLoading()
    //无网络
    fun error()
    //隐藏错误框
    fun dissError()


}