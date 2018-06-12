package hao.wen.zhang.kotlindemo.module

/**
 * 作者：ZWH
 * 创建日期： 2018/6/8 0008   下午 1:26
 * 描述说明：
 */

data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(
            val tabList: List<Tab>,
            val defaultIdx: Int
    ) {
        data class Tab(
                val id: Int,
                val name: String,
                val apiUrl: String
        )
    }
}