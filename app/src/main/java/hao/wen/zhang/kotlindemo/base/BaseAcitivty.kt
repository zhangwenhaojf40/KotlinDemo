package hao.wen.zhang.kotlindemo.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.gyf.barlibrary.ImmersionBar
import hao.wen.zhang.kotlindemo.R
import kotlinx.android.synthetic.main.base_layout.*

/**
 * 作者：ZWH
 * 创建日期： 2018/6/5 0005   上午 10:34
 * 描述说明：
 */
abstract class BaseAcitivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransBar()

        setContentView(R.layout.base_layout)
        LayoutInflater.from(this).inflate(getRes(),content,true)
        initView()

        initData()

    }

    private fun setTransBar() {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor("#ffffff")
//                .flymeOSStatusBarFontColor("#000000")  //修改flyme OS状态栏字体颜色
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun getRes(): Int
     fun jumpActivity( clz:Class<out AppCompatActivity>) {

         startActivity(Intent(this, clz))



     }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }


}