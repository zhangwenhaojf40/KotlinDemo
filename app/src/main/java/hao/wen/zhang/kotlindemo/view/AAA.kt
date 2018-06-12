package hao.wen.zhang.kotlindemo.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hao.wen.zhang.kotlindemo.R
import kotlinx.android.synthetic.main.aa.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/12 0012   上午 11:29
 * 描述说明：
 */
class AAA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aa)
        imageView.background=resources.getDrawable(R.drawable.me_bg)
    }

}