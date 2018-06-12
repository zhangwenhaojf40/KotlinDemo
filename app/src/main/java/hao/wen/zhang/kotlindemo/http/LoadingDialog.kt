package hao.wen.zhang.kotlindemo.http

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import hao.wen.zhang.kotlindemo.R

/**
 * 作者：ZWH
 * 创建日期： 2018/6/7 0007   下午 1:40
 * 描述说明：
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.commom_loading_layout)

    }


}