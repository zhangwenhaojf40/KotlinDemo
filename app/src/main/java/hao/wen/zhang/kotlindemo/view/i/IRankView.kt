package hao.wen.zhang.kotlindemo.view.i

import hao.wen.zhang.kotlindemo.base.IView
import hao.wen.zhang.kotlindemo.module.RankBean

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   下午 2:39
 * 描述说明：
 */
interface IRankView : IView {
    fun getData(bean:RankBean)
}