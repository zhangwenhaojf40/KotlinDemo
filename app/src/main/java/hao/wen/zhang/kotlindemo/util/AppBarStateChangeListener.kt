package hao.wen.zhang.kotlindemo.util

import android.support.design.widget.AppBarLayout

/**
 * 作者：ZWH
 * 创建日期： 2018/6/11 0011   下午 5:45
 * 描述说明：
 */
abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {

    private var mCurrentState = State.IDLE

    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED)
            }
            mCurrentState = State.EXPANDED
        } else if (Math.abs(i) >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED)
            }
            mCurrentState = State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE)
            }
            mCurrentState = State.IDLE
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}