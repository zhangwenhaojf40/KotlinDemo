package hao.wen.zhang.kotlindemo.base

import android.app.Application

/**
 * 作者：ZWH
 * 创建日期： 2018/6/7 0007   下午 1:45
 * 描述说明：
 */
class App : Application() {
    //静态属性，私有化set方法
    companion object {
        lateinit var application: App
            private set
    }


    override fun onCreate() {
        super.onCreate()
        application = this



    }


}